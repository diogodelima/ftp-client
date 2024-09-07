package com.diogoandlucas.ftpclient.controller;

import com.diogoandlucas.ftpclient.exceptions.FTPConnectionAlreadyExistsException;
import com.diogoandlucas.ftpclient.exceptions.FTPException;
import com.diogoandlucas.ftpclient.exceptions.FTPInvalidCredentialsException;
import com.diogoandlucas.ftpclient.exceptions.FTPInvalidServerException;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlCommand;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlFTP;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlResponse;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlResponseCode;
import com.diogoandlucas.ftpclient.model.client.ftp.data.DataFTP;
import com.diogoandlucas.ftpclient.model.client.ftp.data.impl.DataAsciiFTP;
import com.diogoandlucas.ftpclient.model.client.ftp.data.impl.DataBinaryFTP;
import com.diogoandlucas.ftpclient.model.item.Item;
import com.diogoandlucas.ftpclient.model.item.impl.DirectoryItem;
import com.diogoandlucas.ftpclient.model.item.impl.FileItem;
import com.diogoandlucas.ftpclient.model.observer.Observer;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FTPController implements AutoCloseable {

    private ControlFTP controlConnection;
    private DataFTP dataConnection;

    public void connect(String ip, String user, String password) throws FTPInvalidServerException, FTPInvalidCredentialsException, FTPConnectionAlreadyExistsException {

        if (controlConnection != null)
            throw new FTPConnectionAlreadyExistsException();

        this.controlConnection = new ControlFTP(ip);
        ControlResponse response = controlConnection.getResponse();
        if(response.getCode() != ControlResponseCode.CODE_220) throw new FTPInvalidServerException(response);

        response = controlConnection
                .argument(user)
                .sendMessage(ControlCommand.USER);
        if(response.getCode() != ControlResponseCode.CODE_331) throw new FTPInvalidCredentialsException(response);

        response = controlConnection
                .argument(password)
                .sendMessage(ControlCommand.PASS);
        if(response.getCode() != ControlResponseCode.CODE_230) throw new FTPInvalidCredentialsException(response);
    }

    public void enterInPassiveMode(boolean binary) throws FTPException {
        ControlResponse response = controlConnection.sendMessage(ControlCommand.PASV);
        if(response.getCode() != ControlResponseCode.CODE_227) throw new FTPException("Fail entering passive mode", response);
        String[] ipAndPort = controlConnection.getIpAndPort(response.getMessage());
        if(binary)
            this.dataConnection = new DataBinaryFTP(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
        else
            this.dataConnection = new DataAsciiFTP(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
    }

    public String getCurrentDirectory() throws FTPException {

        enterInPassiveMode(false);

        ControlResponse response = controlConnection
                .sendMessage(ControlCommand.PWD);

        if (response.getCode() != ControlResponseCode.CODE_257) throw new FTPException("Failed to get the current directory", response);

        return response.getMessage().substring(response.getMessage().indexOf("\"") + 1, response.getMessage().lastIndexOf("\""));
    }

    public List<Item> getItems() throws FTPException {
        enterInPassiveMode(false);
        ControlResponse response = controlConnection.sendMessage(ControlCommand.MLSD);
        if(response.getCode() != ControlResponseCode.CODE_150) throw new FTPException("Failed to open data connection", response);
        String data = (String) dataConnection.getResponse();
        response = controlConnection.getResponse();
        if (response.getCode() != ControlResponseCode.CODE_226) throw new FTPException("Failed to close data connection", response);
        closeDataConnection();

        String[] singleDataArray = data.split("\n");
        List<Item> items = new ArrayList<>();

        for (String singleData : singleDataArray) {

            String[] information = singleData.split(";");
            String type = information[0].substring(information[0].indexOf("=") + 1);
            long size = Long.parseLong(information[1].substring(information[1].indexOf("=") + 1));
            LocalDateTime lastModify = getLocalDateTime(information);
            String name = information[3].trim();

            Item item;

            if (type.equalsIgnoreCase("dir"))
                item = new DirectoryItem(name, lastModify, size);
            else if (type.equalsIgnoreCase("file"))
                item = new FileItem(name, lastModify, size);
            else throw new IllegalArgumentException("Invalid type " + type);

            items.add(item);
        }

        return items;
    }

    public void makeDirectory(String pathname) throws FTPException {

        ControlResponse response = controlConnection
                .argument(pathname)
                .sendMessage(ControlCommand.MKD);

        if (response.getCode() != ControlResponseCode.CODE_257) throw new FTPException("Failed to create the directory", response);
    }

    public void makeFile(String filename) throws FTPException {

        enterInPassiveMode(false);
        ControlResponse response = controlConnection
                .argument(filename)
                .sendMessage(ControlCommand.STOR);

        System.out.println(response);

        if (response.getCode() != ControlResponseCode.CODE_150) throw new FTPException("Failed to create the file", response);

        closeDataConnection();
        response = controlConnection.getResponse();

        if (response.getCode() != ControlResponseCode.CODE_226) throw new FTPException("Failed to create the file", response);
    }

    public void renameItem(String from, String to) throws FTPException {

        ControlResponse response = controlConnection
                .argument(from)
                .sendMessage(ControlCommand.RNFR);

        if (response.getCode() != ControlResponseCode.CODE_350) throw new FTPException("Failed to rename the file", response);

        response = controlConnection
                .argument(to)
                .sendMessage(ControlCommand.RNTO);

        if (response.getCode() != ControlResponseCode.CODE_250) throw new FTPException("Failed to rename the file", response);
    }

    public void removeDirectory(String pathname) throws FTPException {

        ControlResponse response = controlConnection
                .argument(pathname)
                .sendMessage(ControlCommand.RMD);

        if (response.getCode() != ControlResponseCode.CODE_250) throw new FTPException("Failed to remove directory", response);
    }

    public void removeFile(String pathname) throws FTPException {

        ControlResponse response = controlConnection
                .argument(pathname)
                .sendMessage(ControlCommand.DELE);

        if (response.getCode() != ControlResponseCode.CODE_250) throw new FTPException("Failed to remove file", response);
    }

    public int getSizeOfFile(String pathname) throws FTPException {

        ControlResponse response = controlConnection
                .argument(pathname)
                .sendMessage(ControlCommand.SIZE);

        if (response.getCode() != ControlResponseCode.CODE_213) throw new FTPException("Failed to remove file", response);
        return Integer.parseInt(response.getMessage().trim());
    }

    @Override
    public void close() throws Exception {
        this.controlConnection.close();
        closeDataConnection();
    }

    private void closeDataConnection() {
        if(dataConnection != null) {
            try {
                dataConnection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static LocalDateTime getLocalDateTime(String[] information) {
        String dateFormatted = information[2].substring(information[2].indexOf("=") + 1);
        int year = Integer.parseInt(dateFormatted.substring(0, 4));
        int month = Integer.parseInt(dateFormatted.substring(4, 6));
        int day = Integer.parseInt(dateFormatted.substring(6, 8));
        int hour = Integer.parseInt(dateFormatted.substring(8, 10));
        int minute = Integer.parseInt(dateFormatted.substring(10, 12));
        int second = Integer.parseInt(dateFormatted.substring(12, 14));
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    public void setBinaryMode() throws FTPException {

        ControlResponse response = controlConnection
                .argument("I")
                .sendMessage(ControlCommand.TYPE);

        if(response.getCode() != ControlResponseCode.CODE_200) throw new FTPException("Failed to enter in Binary mode", response);
    }

    public void setAsciiMode() throws FTPException {
        ControlResponse response = controlConnection
                .argument("A")
                .sendMessage(ControlCommand.TYPE);
        if(response.getCode() != ControlResponseCode.CODE_200) throw new FTPException("Failed to enter in ASCII mode", response);
    }

    public CompletableFuture<Void> downloadFile(String pathname, String localPathname, Observer observer) throws FTPException {

        enterInPassiveMode(true);

        DataBinaryFTP dataBinaryFTP = (DataBinaryFTP) this.dataConnection;
        dataBinaryFTP.setTotalBytesToRead(getSizeOfFile(pathname));

        if (observer != null)
            ((DataBinaryFTP) this.dataConnection).addObserver(observer);

        ControlResponse response = controlConnection
                .argument(pathname)
                .sendMessage(ControlCommand.RETR);

        if(response.getCode() != ControlResponseCode.CODE_150) throw new FTPException("Failed to download file", response);

        return CompletableFuture.supplyAsync(() -> {

            String finalLocalPathname;
            if(pathname.contains("/"))
                finalLocalPathname = localPathname + pathname.substring(pathname.lastIndexOf("/"));
            else
                finalLocalPathname = localPathname + pathname;

            try(FileOutputStream fileOutputStream = new FileOutputStream(finalLocalPathname);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);){

                byte[] data = (byte[]) this.dataConnection.getResponse();
                bufferedOutputStream.write(data);
                bufferedOutputStream.flush();
                ControlResponse closeDataResponse = controlConnection.getResponse();
                if (closeDataResponse.getCode() != ControlResponseCode.CODE_226) throw new FTPException("Failed to close data connection", response);
                closeDataConnection();
                return null;

            }catch (IOException | FTPException e) {
                throw new RuntimeException(e);
            }

        });

    }

    public CompletableFuture<Void> downloadFile(String pathname, String localPathname) throws FTPException {
        return downloadFile(pathname, localPathname, null);
    }

}
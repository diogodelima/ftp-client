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
import com.diogoandlucas.ftpclient.model.client.item.Item;
import com.diogoandlucas.ftpclient.model.client.item.impl.DirectoryItem;
import com.diogoandlucas.ftpclient.model.client.item.impl.FileItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public void enterInPassiveMode() throws FTPException {
        ControlResponse response = controlConnection.sendMessage(ControlCommand.PASV);
        if(response.getCode() != ControlResponseCode.CODE_227) throw new FTPException("Fail entering passive mode", response);
        String[] ipAndPort = controlConnection.getIpAndPort(response.getMessage());
        this.dataConnection = new DataFTP(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
    }

    public List<Item> getItems() throws FTPException {
        enterInPassiveMode();
        ControlResponse response = controlConnection.sendMessage(ControlCommand.MLSD);
        if(response.getCode() != ControlResponseCode.CODE_150) throw new FTPException("Failed to open data connection", response);
        String data = dataConnection.getResponse();
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

}

package com.diogoandlucas.ftpclient.controller;

import com.diogoandlucas.ftpclient.exceptions.FTPException;
import com.diogoandlucas.ftpclient.exceptions.FTPInvalidCredentialsException;
import com.diogoandlucas.ftpclient.exceptions.FTPInvalidServerException;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlCommand;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlFTP;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlResponse;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlResponseCode;
import com.diogoandlucas.ftpclient.model.client.ftp.data.DataFTP;

import java.util.List;

public class FTPController implements AutoCloseable {

private final ControlFTP controlConnection;
private DataFTP dataConnection;

    public FTPController(String ip, String user, String password) throws FTPInvalidServerException, FTPInvalidCredentialsException {

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

    public String getDirectories() throws FTPException {
        enterInPassiveMode();
        ControlResponse response = controlConnection.sendMessage(ControlCommand.MLSD);
        if(response.getCode() != ControlResponseCode.CODE_150) throw new FTPException("Failed to open data connection", response);
        String directories = dataConnection.getResponse();
        response = controlConnection.getResponse();
        if (response.getCode() != ControlResponseCode.CODE_226) throw new FTPException("Failed to close data connection", response);
        return directories;
    }

    public void makeDirectory(String pathname) throws FTPException {

        ControlResponse response = controlConnection
                .argument(pathname)
                .sendMessage(ControlCommand.MKD);

        if (response.getCode() != ControlResponseCode.CODE_257) throw new FTPException("Failed to create the directory", response);
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

}

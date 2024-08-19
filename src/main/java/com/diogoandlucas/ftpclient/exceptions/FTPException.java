package com.diogoandlucas.ftpclient.exceptions;

import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlResponse;

public class FTPException extends Exception{

    private final ControlResponse response;

    public FTPException(String message, ControlResponse response){
        super(message);
        this.response = response;
    }

    public ControlResponse getResponse() {
        return response;
    }
}

package com.diogoandlucas.ftpclient.exceptions;

import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlResponse;

public class FTPInvalidCredentialsException extends FTPException{


    public FTPInvalidCredentialsException(ControlResponse response) {
        super("Invalid Credentials", response);
    }
}

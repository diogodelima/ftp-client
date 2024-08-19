package com.diogoandlucas.ftpclient.exceptions;

import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlResponse;

public class FTPInvalidServerException extends FTPException{

    public FTPInvalidServerException(ControlResponse response){
        super("Server Invalid", response);
    }

}

package com.diogoandlucas.ftpclient.exceptions;

public class FTPConnectionAlreadyExistsException extends RuntimeException {

    public FTPConnectionAlreadyExistsException(String message) {
        super(message);
    }

    public FTPConnectionAlreadyExistsException() {
        super("FTP connection already exists");
    }

}

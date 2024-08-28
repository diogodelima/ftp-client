package com.diogoandlucas.ftpclient.model.client.ftp.data;

import com.diogoandlucas.ftpclient.model.client.Client;

public abstract class DataFTP<T> extends Client<T, String> {


    public DataFTP(String ip, int port) {
        super(ip, port);
    }

    @Override
    public T sendMessage(String message) {
        return getResponse();
    }

}


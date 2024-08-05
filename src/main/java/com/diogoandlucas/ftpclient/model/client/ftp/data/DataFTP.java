package com.diogoandlucas.ftpclient.model.client.ftp.data;

import com.diogoandlucas.ftpclient.model.client.Client;

public class DataFTP extends Client<String, String> {

    protected DataFTP(String ip) {
        super(ip, 20);
    }

    @Override
    public String sendMessage(String message) {
        return "";
    }

    @Override
    public String getResponse() {
        return "";
    }
}

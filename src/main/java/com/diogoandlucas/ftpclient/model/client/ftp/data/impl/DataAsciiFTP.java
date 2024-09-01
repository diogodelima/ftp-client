package com.diogoandlucas.ftpclient.model.client.ftp.data.impl;

import com.diogoandlucas.ftpclient.model.client.ftp.data.DataFTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataAsciiFTP extends DataFTP<String> {

    private final BufferedReader in;

    public DataAsciiFTP(String ip, int port) {
        super(ip, port);
        this.in = new BufferedReader(new InputStreamReader(this.getIn()));
    }

    @Override
    public String getResponse() {

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = in.readLine()) != null)
                stringBuilder.append(line).append("\n");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

}
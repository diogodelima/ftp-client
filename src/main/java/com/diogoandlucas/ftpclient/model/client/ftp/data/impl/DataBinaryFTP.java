package com.diogoandlucas.ftpclient.model.client.ftp.data.impl;

import com.diogoandlucas.ftpclient.model.client.ftp.data.DataFTP;
import com.diogoandlucas.ftpclient.model.observer.Observer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataBinaryFTP extends DataFTP<byte[]> {

    private final List<Observer> observers = new ArrayList<>();
    private int totalBytesToRead;

    public DataBinaryFTP(String ip, int port) {
        super(ip, port);
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void notifyObservers(double byteRate, long elapsedTime, int bytesRead) {

        for (Observer observer : this.observers)
            observer.update(byteRate, elapsedTime, bytesRead, this.totalBytesToRead);

    }

    public void setTotalBytesToRead(int totalBytesToRead) {
        this.totalBytesToRead = totalBytesToRead;
    }

    @Override
    public byte[] getResponse() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        long startTime = System.currentTimeMillis();
        int totalBytesRead = 0;
        int bytesRead;

        try {

            while ((bytesRead = this.getIn().read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                long elapsedTime = System.currentTimeMillis() - startTime;
                double byteRate = (double) totalBytesRead / (double) elapsedTime;
                notifyObservers(byteRate, elapsedTime, totalBytesRead);
            }

        }catch(IOException e){
            throw new RuntimeException(e);
        }

        return byteArrayOutputStream.toByteArray();
    }

}
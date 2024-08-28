
package com.diogoandlucas.ftpclient.model.client.ftp.data.impl;

import com.diogoandlucas.ftpclient.model.client.ftp.data.DataFTP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DataBinaryFTP extends DataFTP<byte[]> {

    public DataBinaryFTP(String ip, int port) {
        super(ip, port);
    }

    @Override
    public byte[] getResponse() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        try {
            while ((bytesRead = this.getIn().read(buffer)) != -1)
                byteArrayOutputStream.write(buffer, 0, bytesRead);
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        return byteArrayOutputStream.toByteArray();
    }

}
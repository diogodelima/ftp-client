package com.diogoandlucas.ftpclient.model.client;

import java.io.*;
import java.net.Socket;

public abstract class Client<T, V> implements AutoCloseable {

    protected final Socket client;
    private final InputStream in;
    private final OutputStream out;

    public Client(String ip, int port) {

        try {
            this.client = new Socket(ip, port);
            this.in = client.getInputStream();
            this.out = client.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public abstract T sendMessage(V message);

    public abstract T getResponse();

    public InputStream getIn() {
        return in;
    }

    public OutputStream getOut() {
        return out;
    }

    @Override
    public void close() throws Exception {
        in.close();
        out.close();
        client.close();
    }

}

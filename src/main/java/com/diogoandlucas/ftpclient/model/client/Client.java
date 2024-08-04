package com.diogoandlucas.ftpclient.model.client;

import java.io.*;
import java.net.Socket;

public abstract class Client<T> implements AutoCloseable {

    protected final Socket client;
    protected final BufferedReader in;
    protected final PrintWriter out;

    public Client(String ip, int port) {

        try {
            this.client = new Socket(ip, port);
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public T sendMessage(String message) throws IOException {
        this.out.println(message);
        return getResponse();
    }

    public abstract T getResponse() throws IOException;

    @Override
    public void close() throws Exception {
        in.close();
        out.close();
        client.close();
    }

}

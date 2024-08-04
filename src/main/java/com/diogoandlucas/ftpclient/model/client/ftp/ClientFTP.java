package com.diogoandlucas.ftpclient.model.client.ftp;

import com.diogoandlucas.ftpclient.model.client.Client;

import java.io.IOException;

public class ClientFTP extends Client<Response> {

    protected ClientFTP(String ip, int port) {
        super(ip, port);
    }

    @Override
    public Response getResponse() throws IOException {
        String line = this.in.readLine();
        ResponseCode code = ResponseCode.valueOf("CODE_" + line.substring(0, line.indexOf(" ")));
        String message = line.substring(line.indexOf(" "));
        return new Response(code, message);
    }

}

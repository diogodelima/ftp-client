package com.diogoandlucas.ftpclient.model.client.ftp.control;

import com.diogoandlucas.ftpclient.model.client.Client;
import com.diogoandlucas.ftpclient.model.client.ftp.data.DataFTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ControlFTP extends Client<ControlResponse, String> {

    private final PrintWriter out;
    private final BufferedReader in;
    private DataFTP data;

    public ControlFTP(String ip) {
        super(ip, 21);
        this.out = new PrintWriter(this.getOut(), true);
        this.in = new BufferedReader(new InputStreamReader(this.getIn()));
    }

    @Override
    public ControlResponse sendMessage(String message) {
        this.out.write(message);
        return this.getResponse();
    }

    @Override
    public ControlResponse getResponse() {
        try {
            String line = this.in.readLine();
            ControlResponseCode code = ControlResponseCode.valueOf("CODE_" + line.substring(0, line.indexOf(" ")));
            String message = line.substring(line.indexOf(" "));
            return new ControlResponse(code, message);
        }catch (Exception e) {
            throw new RuntimeException("Error getting response from server", e);
        }
    }

    public String[] getIpAndPort(String message){
        String subPart = message.substring(message.indexOf("(")+1, message.indexOf(")"));
        String[] array = subPart.split(",");
        String ip = array[0] + "." + array[1] + "." + array[2] + "." + array[3];
        int port = (Integer.parseInt(array[4]) * 256) + Integer.parseInt(array[5]);
        return new String[]{ip, String.valueOf(port)};
    }

}

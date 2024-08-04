package com.diogoandlucas.ftpclient.model.client.ftp;

public class ControlFTP extends ClientFTP {

    private DataFTP data;

    public ControlFTP(String ip) {
        super(ip, 21);
    }

    public String[] getIpAndPort(String message){
        String subPart = message.substring(message.indexOf("(")+1, message.indexOf(")"));
        String[] array = subPart.split(",");
        String ip = array[0] + "." + array[1] + "." + array[2] + "." + array[3];
        int port = (Integer.parseInt(array[4]) * 256) + Integer.parseInt(array[5]);
        return new String[]{ip, String.valueOf(port)};
    }

}

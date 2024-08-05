package com.diogoandlucas.ftpclient;

import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlFTP;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlResponse;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) throws Exception {

        System.out.println("inicio");
        ControlFTP client = new ControlFTP("eu-central-1.sftpcloud.io");
        ControlResponse response = client.getResponse();
        System.out.println(response);
        response = client.sendMessage("USER d60625c2599e4c3382f27e046126d894");
        System.out.println(response);
        response = client.sendMessage("PASS 7f9SgvxkeajTZqrJwhMnPzBmrPVIHCFs");
        System.out.println(response);
        System.out.println(response);
        response = client.sendMessage("PASV");
        System.out.println(response);
        System.out.println(Arrays.toString(client.getIpAndPort(response.getMessage())));
        response = client.sendMessage("PWD");
        System.out.println(response);
        response = client.sendMessage("MLSD");
        System.out.println(response);
        client.close();
    }

}

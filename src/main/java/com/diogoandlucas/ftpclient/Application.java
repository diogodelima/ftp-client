package com.diogoandlucas.ftpclient;

import com.diogoandlucas.ftpclient.controller.FTPController;
import com.diogoandlucas.ftpclient.exceptions.FTPException;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlCommand;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlFTP;
import com.diogoandlucas.ftpclient.model.client.ftp.control.ControlResponse;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) throws Exception {

        System.out.println("inicio");
        /*ControlFTP client = new ControlFTP("eu-central-1.sftpcloud.io");
        ControlResponse response = client.getResponse();
        System.out.println(response);
        response = client
                .argument("33975ed0bd3746b6986c4a48a8fbcfbf")
                .sendMessage(ControlCommand.USER);
        System.out.println(response);
        response = client
                .argument("zKpaLansmEIQMsYEMDo8tO2J1FhcpOiV")
                .sendMessage(ControlCommand.PASS);
        System.out.println(response);
        System.out.println(response);
        response = client
                .sendMessage(ControlCommand.PASV);
        System.out.println(response);
        System.out.println(Arrays.toString(client.getIpAndPort(response.getMessage())));
        response = client
                .sendMessage(ControlCommand.PWD);
        System.out.println(response);
        response = client
                .sendMessage(ControlCommand.MLSD);
        System.out.println(response);
        client.close();*/
        try(FTPController controller = new FTPController("eu-central-1.sftpcloud.io", "12cf868e95b3428a8a860d787e2767e8", "PH1s8OqL5J0RmKlxTw4axN7AoKG609TZ")){
            System.out.println(controller.getDirectories());
        }catch(FTPException e){
            System.out.println(e.getResponse());
        };

    }

}

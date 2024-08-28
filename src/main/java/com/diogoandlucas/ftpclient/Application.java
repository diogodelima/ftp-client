package com.diogoandlucas.ftpclient;

import com.diogoandlucas.ftpclient.controller.FTPController;
import com.diogoandlucas.ftpclient.view.View;

import java.io.File;

public class Application {

    public static void main(String[] args) throws Exception {

        try(FTPController controller = new FTPController()){
            controller.connect("eu-central-1.sftpcloud.io", "a7504c2c76c0471c83fe5dc82a8d572d", "ebZRhyS6Dtf3VwTlgsMNLu9Ltsfg5sZE");
            File data = controller.downloadFile("teste.txt", "C:\\Projects\\ClientFTP\\workbench\\");
            System.out.println(data);
            System.out.println(controller.getItems());
        }catch(Exception e){
            e.printStackTrace();
        }

        new View(1280, 720);
    }

}
package com.diogoandlucas.ftpclient;

import com.diogoandlucas.ftpclient.controller.FTPController;
import com.diogoandlucas.ftpclient.exceptions.FTPException;
import com.diogoandlucas.ftpclient.view.View;

import java.io.File;

public class Application {

    public static void main(String[] args) throws Exception {

        try(FTPController controller = new FTPController()){
            controller.connect("eu-central-1.sftpcloud.io", "8031e23a49ba42ecad8f98104adc3b1d", "eRhLGsLIUOZPQdfP0xarshL1JNJJr4J9");
            File data = controller.downloadFile("teste.7z", "C:\\Users\\diogo\\Desktop\\", (byteRate, elapsedTime) -> {
                System.out.println("Byte Rate: " + byteRate);
                System.out.println("Elapsed Time: " + elapsedTime);
            });
            System.out.println(data);
            System.out.println(controller.getItems());
        }catch(FTPException e){
            System.out.println(e.getResponse());
        }

        new View(1280, 720);
    }

}
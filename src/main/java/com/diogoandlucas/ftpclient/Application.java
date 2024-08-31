package com.diogoandlucas.ftpclient;

import com.diogoandlucas.ftpclient.controller.FTPController;
import com.diogoandlucas.ftpclient.exceptions.FTPException;
import com.diogoandlucas.ftpclient.view.View;

public class Application {

    public static void main(String[] args) throws Exception {

        try(FTPController controller = new FTPController()){
            controller.connect("eu-central-1.sftpcloud.io", "e9da2d0853a44056a8e4a755238768b9", "SGFaN0CbqCTn70mKUgXxp6CHTMNGDnB4");
            System.out.println(controller.getCurrentDirectory());
        }catch(FTPException e){
            System.out.println(e.getResponse());
        }

        new View(1280, 720);
    }

}
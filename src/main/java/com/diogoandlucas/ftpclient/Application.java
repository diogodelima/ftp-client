package com.diogoandlucas.ftpclient;

import com.diogoandlucas.ftpclient.controller.FTPController;
import com.diogoandlucas.ftpclient.exceptions.FTPException;
import com.diogoandlucas.ftpclient.view.View;
import com.diogoandlucas.ftpclient.view.panel.tranfer.icon.TransferBar;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Application {

    public static void main(String[] args) throws Exception {

        TransferBar transferBar = new TransferBar();
        new View(1280, 720);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.add(transferBar);
        frame.setVisible(true);

        try(FTPController controller = new FTPController()){
            controller.connect("eu-central-1.sftpcloud.io", "2fbd81d21b214e81bcb74c1c990ca026", "xOrgWmkIgBWRlZChgObFAzt2Ul0DsXkR");
            File data = controller.downloadFile("teste.7z", "C:\\Users\\diogo\\Desktop\\", transferBar);
            System.out.println(data);


            System.out.println(controller.getSizeOfFile("teste.txt"));
            System.out.println(controller.getItems());
        }catch(FTPException e){
            System.out.println(e.getResponse());
        }

    }

}
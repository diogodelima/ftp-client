package com.diogoandlucas.ftpclient.view;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.controller.FTPController;
import com.diogoandlucas.ftpclient.view.panel.CredentialsPanel;
import com.diogoandlucas.ftpclient.view.panel.file.FilePanel;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private final FTPController ftpController = new FTPController();

    public View(int width, int height){

        this.setSize(width, height);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(new CredentialsPanel(ftpController, this), BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setBackground(ColorConstants.BACKGROUND);
        this.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(1, 2, 10, 5));
        panel.add(new FilePanel("Endereço local:"));
        panel.add(new FilePanel("Endereço remoto:"));

        this.setVisible(true);
    }

}

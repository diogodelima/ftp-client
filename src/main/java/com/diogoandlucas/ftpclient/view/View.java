package com.diogoandlucas.ftpclient.view;

import com.diogoandlucas.ftpclient.view.panel.CredentialsPanel;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    public View(int width, int height){

        this.setSize(width, height);
        this.setLayout(new BorderLayout());
        this.add(new CredentialsPanel(), BorderLayout.NORTH);
        this.setVisible(true);
    }

}

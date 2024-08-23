package com.diogoandlucas.ftpclient.view.panel;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.controller.FTPController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import static com.diogoandlucas.ftpclient.view.util.ViewUtil.*;

public class CredentialsPanel extends JPanel {

    public CredentialsPanel (FTPController ftpController){
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        this.setBackground(ColorConstants.BACKGROUND);
        JLabel serverLabel, userLabel, passwordLabel;
        JTextField serverTextField, userTextField, passwordTextField;
        JButton connectButton;
        serverLabel = createLabel("Servidor:", ColorConstants.LABEL);
        userLabel = createLabel("Utilizador:", ColorConstants.LABEL);
        passwordLabel = createLabel("Password:", ColorConstants.LABEL);
        serverTextField = createTextField(12, ColorConstants.FIELD, ColorConstants.LABEL);
        userTextField = createTextField(10, ColorConstants.FIELD, ColorConstants.LABEL);
        passwordTextField = createTextField(10, ColorConstants.FIELD, ColorConstants.LABEL);
        connectButton = createButton("Conectar", ColorConstants.FIELD, ColorConstants.LABEL, ColorConstants.HOVER_BACKGROUND, ColorConstants.CLICK_BACKGROUND);
        this.add(serverLabel);
        this.add(serverTextField);
        this.add(userLabel);
        this.add(userTextField);
        this.add(passwordLabel);
        this.add(passwordTextField);
        this.add(connectButton);
    }

}

package com.diogoandlucas.ftpclient.view.panel;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.controller.FTPController;
import com.diogoandlucas.ftpclient.exceptions.FTPConnectionAlreadyExistsException;
import com.diogoandlucas.ftpclient.exceptions.FTPInvalidCredentialsException;
import com.diogoandlucas.ftpclient.exceptions.FTPInvalidServerException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import static com.diogoandlucas.ftpclient.view.util.ViewUtil.*;

public class CredentialsPanel extends JPanel {

    private final JTextField serverTextField, userTextField, passwordTextField;
    private final FTPController ftpController;
    private final JFrame frame;


    public CredentialsPanel (FTPController ftpController, JFrame frame){
        this.ftpController = ftpController;
        this.frame = frame;
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        this.setBackground(ColorConstants.BACKGROUND);
        JLabel serverLabel, userLabel, passwordLabel;
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
        connectButton.addActionListener(this::buttonListener);
    }

    private void buttonListener(ActionEvent event){
        try {
            ftpController.connect(serverTextField.getText(), userTextField.getText(), passwordTextField.getText());
        } catch (FTPInvalidServerException e) {
            throw new RuntimeException(e);
        } catch (FTPInvalidCredentialsException e) {
            throw new RuntimeException(e);
        } catch (FTPConnectionAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            System.out.println("testee");
            createDialog(frame, "FTPException");
        }
    }

}

package com.diogoandlucas.ftpclient.view.panel;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.controller.FTPController;
import com.diogoandlucas.ftpclient.exceptions.FTPConnectionAlreadyExistsException;
import com.diogoandlucas.ftpclient.exceptions.FTPInvalidCredentialsException;
import com.diogoandlucas.ftpclient.exceptions.FTPInvalidServerException;
import com.diogoandlucas.ftpclient.view.popup.Popup;
import com.diogoandlucas.ftpclient.view.popup.PopupBuilder;
import com.diogoandlucas.ftpclient.view.popup.item.PopupItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.SocketException;
import java.rmi.UnknownHostException;

import static com.diogoandlucas.ftpclient.view.util.ViewUtil.*;

public class CredentialsPanel extends JPanel {

    private final JTextField serverTextField, userTextField, passwordTextField;
    //private final JPasswordField passwordField;
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
        serverTextField = createTextField(12, ColorConstants.FIELD, ColorConstants.LABEL, false);
        userTextField = createTextField(10, ColorConstants.FIELD, ColorConstants.LABEL, false);
        passwordTextField = (JPasswordField) createTextField(10, ColorConstants.FIELD, ColorConstants.LABEL, true);
        connectButton = createButton("Conectar", ColorConstants.FIELD, ColorConstants.LABEL, ColorConstants.HOVER_BACKGROUND, ColorConstants.CLICK_BACKGROUND);
        this.add(serverLabel);
        this.add(serverTextField);
        this.add(userLabel);
        this.add(userTextField);
        this.add(passwordLabel);
        this.add(passwordTextField);
        this.add(connectButton);
        connectButton.addActionListener(this::buttonListener);

        Popup popup = createPopupTextField(serverTextField);
    }

    private void buttonListener(ActionEvent event){
        try {
            if(serverTextField.getText() == null || userTextField.getText() == null || passwordTextField.getText() == null
                    || serverTextField.getText().isEmpty() || userTextField.getText().isEmpty() || passwordTextField.getText().isEmpty())
                throw new FTPInvalidServerException(null);
            ftpController.connect(serverTextField.getText(), userTextField.getText(), passwordTextField.getText());
        } catch (FTPInvalidServerException e) {
            createDialog(frame, "Servidor indisponível", "Erro");
        } catch (FTPInvalidCredentialsException e) {
            createDialog(frame, "<html>Credenciais incorretas!</html>", "Erro");
        } catch (FTPConnectionAlreadyExistsException e) {
            createDialog(frame, "<html>Já existe uma conexão para o servidor!</html>", "Erro");
        } catch (RuntimeException e){

            if(e.getCause() != null && (e.getCause() instanceof UnknownHostException || e.getCause() instanceof SocketException)) {
                createDialog(frame, "Servidor indisponível", "Erro");
                return;
            }

            createDialog(frame, "<html>Ocorreu um erro inesperado!<br/>Contacte o administrador.</html>", "Erro");
            throw e;
        }
    }

}

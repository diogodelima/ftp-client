package com.diogoandlucas.ftpclient.view.util;

import com.diogoandlucas.ftpclient.view.components.RoundedBorder;
import com.diogoandlucas.ftpclient.view.components.RoundedButton;
import com.diogoandlucas.ftpclient.view.components.RoundedTextField;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static java.awt.SystemColor.text;

public class ViewUtil{

    public static JLabel createLabel(String text, Color foreground){
        JLabel label = new JLabel(text);
        label.setForeground(foreground);
        return label;
    }

    public static JTextField createTextField(int columns, Color background, Color foreground){
        JTextField textField = new RoundedTextField(columns);
        textField.setBackground(background);
        textField.setForeground(foreground);
        return textField;
    }

    public static JButton createButton(String text, Color background, Color foreground, Color hover, Color click){
        RoundedButton button = new RoundedButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setClickBackground(click);
        button.setHoverBackground(hover);
        //tirar a cor do botao ao clicar
        return button;
    }

}

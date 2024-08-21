package com.diogoandlucas.ftpclient.view.util;

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

    public static JTextField createTextField(int columns, Color background, Color foreground, Border border){
        JTextField textField = new JTextField(columns);
        textField.setBorder(border);
        textField.setBackground(background);
        textField.setForeground(foreground);
        return textField;
    }

    public static JTextField createTextField(int columns, Color background, Color foreground, int horizontal, int vertical){
        return createTextField(columns, background, foreground,BorderFactory.createEmptyBorder(horizontal, horizontal, vertical, vertical));
    }

    public static JButton createButton(String text, Color background, Color foreground, int horizontal, int vertical){
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setBorder(BorderFactory.createEmptyBorder(horizontal, horizontal, vertical, vertical));
        //tirar a cor do botao ao clicar
        return button;
    }

}

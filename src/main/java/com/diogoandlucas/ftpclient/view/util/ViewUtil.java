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

}

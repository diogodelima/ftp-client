package com.diogoandlucas.ftpclient.view.util;

import com.diogoandlucas.ftpclient.Application;
import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.view.components.RoundedBorder;
import com.diogoandlucas.ftpclient.view.components.RoundedButton;
import com.diogoandlucas.ftpclient.view.components.RoundedTextField;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Objects;

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
        textField.setCaretColor(foreground);
        return textField;
    }

    public static JButton createButton(String text, Color background, Color foreground, Color hover, Color click){
        RoundedButton button = new RoundedButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setClickBackground(click);
        button.setHoverBackground(hover);
        return button;
    }

    public static JDialog createDialog(JFrame frame, String text, String title){
        JDialog dialog = new JDialog(frame, title);
        dialog.setLayout(new BorderLayout(10, 0));
        dialog.setSize(300, 150);

        JLabel iconLabel = new JLabel(new ImageIcon(Objects.requireNonNull(Application.class.getClassLoader().getResource("warningIcon.png"))));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        dialog.add(iconLabel, BorderLayout.WEST);
        dialog.add(createLabel(text, ColorConstants.LABEL), BorderLayout.CENTER);

        JButton button = createButton("   Ok   ", ColorConstants.FIELD, ColorConstants.LABEL, ColorConstants.HOVER_BACKGROUND, ColorConstants.CLICK_BACKGROUND);
        button.addActionListener(event -> dialog.dispose());

        JPanel southPanel = new JPanel(new BorderLayout(10, 10));
        southPanel.setBackground(ColorConstants.BACKGROUND);
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        southPanel.add(button, BorderLayout.EAST);

        dialog.add(southPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(frame);
        dialog.getContentPane().setBackground(ColorConstants.BACKGROUND);
        dialog.setResizable(false);
        dialog.setVisible(true);

        return dialog;
    }

}

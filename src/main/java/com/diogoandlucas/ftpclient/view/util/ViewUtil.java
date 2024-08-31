package com.diogoandlucas.ftpclient.view.util;

import com.diogoandlucas.ftpclient.Application;
import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.controller.FTPController;
import com.diogoandlucas.ftpclient.view.components.RoundedButton;
import com.diogoandlucas.ftpclient.view.components.RoundedPasswordField;
import com.diogoandlucas.ftpclient.view.components.RoundedTextField;
import com.diogoandlucas.ftpclient.view.popup.Popup;
import com.diogoandlucas.ftpclient.view.popup.PopupBuilder;
import com.diogoandlucas.ftpclient.view.popup.item.PopupItem;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.function.Consumer;

import static java.awt.SystemColor.text;

public class ViewUtil{

    public static JLabel createLabel(String text, Color foreground){
        JLabel label = new JLabel(text);
        label.setForeground(foreground);
        return label;
    }

    public static JTextField createTextField(int columns, Color background, Color foreground, boolean password){
        JTextField textField;
        if(password)
            textField = new RoundedPasswordField(columns);
        else
            textField = new RoundedTextField(columns);
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

    public static JDialog createWarningDialog(JFrame frame, String text, String title){
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

    public static JDialog createInputDialog(JFrame frame, String text, String title){
        JDialog dialog = new JDialog(frame, title);
        dialog.setLayout(new BorderLayout(10, 0));
        dialog.setSize(300, 150);

        JLabel label = createLabel(text, ColorConstants.LABEL);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dialog.add(label, BorderLayout.NORTH);

        JTextField inputField = createTextField(20, ColorConstants.FIELD, ColorConstants.LABEL, false);
        //dialog.add(inputField, BorderLayout.CENTER);
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5, 0));
        centerPanel.setBackground(ColorConstants.BACKGROUND);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        centerPanel.add(inputField);

        JButton okButton = createButton("   Ok   ", ColorConstants.FIELD, ColorConstants.LABEL, ColorConstants.HOVER_BACKGROUND, ColorConstants.CLICK_BACKGROUND);
        okButton.addActionListener(event -> dialog.dispose());
        JButton cancelarButton = createButton("   Cancelar   ", ColorConstants.FIELD, ColorConstants.LABEL, ColorConstants.HOVER_BACKGROUND, ColorConstants.CLICK_BACKGROUND);
        cancelarButton.addActionListener(event -> dialog.dispose());

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,10, 10));
        southPanel.setBackground(ColorConstants.BACKGROUND);
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        southPanel.add(okButton);
        southPanel.add(cancelarButton);

        dialog.add(centerPanel, BorderLayout.CENTER);
        dialog.add(southPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(frame);
        dialog.getContentPane().setBackground(ColorConstants.BACKGROUND);
        dialog.setResizable(false);
        dialog.setVisible(true);

        return dialog;
    }


    public static Popup createPopupTextField(JTextField textField){

        Consumer<PopupItem> enableOnSelect = popupItem -> {

            textField.addCaretListener(e -> {
                popupItem.setEnabled(textField.getSelectedText() != null && !textField.getSelectedText().isEmpty());
            });
        };

        Consumer<ActionEvent> copy = e -> {
            StringSelection stringSelection = new StringSelection(textField.getSelectedText());

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        };

        Consumer<ActionEvent> delete = e -> textField.replaceSelection("");


        return PopupBuilder
                .create()
                .setComponent(textField)
                .addItem(new PopupItem("Eliminar tudo", e -> textField.setText("")))
                .addSeparator()
                .addItem(new PopupItem("Copiar", copy, enableOnSelect))
                .addItem(new PopupItem("Colar", e -> {

                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable contents = clipboard.getContents(null);

                    if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        try {
                            String text = (String) contents.getTransferData(DataFlavor.stringFlavor);
                            textField.replaceSelection(text);
                        } catch (Exception ignored) {}
                    }

                }))
                .addItem(new PopupItem("Eliminar", delete, enableOnSelect))
                .addItem(new PopupItem("Cortar", e -> {

                    copy.accept(e);
                    delete.accept(e);

                }, enableOnSelect))
                .addSeparator()
                .addItem(new PopupItem("Selecionar tudo", e -> textField.selectAll()))
                .build();
    }

    public static Popup createPopupServer(JScrollPane panel, FTPController controller){

        return PopupBuilder
                .create()
                .setComponent(panel)
                .addItem(new PopupItem("Transferir", e -> {


                    //controller.makeDirectory()


                }))
                .addSeparator()
                .addItem(new PopupItem("Criar Pasta", e -> {

                }))
                .addItem(new PopupItem("Criar Ficheiro", e -> System.out.println()))
                .addItem(new PopupItem("Atualizar", e -> System.out.println()))
                .addSeparator()
                .addItem(new PopupItem("Apagar", e -> System.out.println()))
                .addItem(new PopupItem("Mudar o nome", e -> System.out.println()))
                .build();
    }

}

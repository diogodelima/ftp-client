package com.diogoandlucas.ftpclient.view.util;

import com.diogoandlucas.ftpclient.Application;
import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.controller.FTPController;
import com.diogoandlucas.ftpclient.exceptions.FTPException;
import com.diogoandlucas.ftpclient.model.item.Item;
import com.diogoandlucas.ftpclient.model.item.impl.FileItem;
import com.diogoandlucas.ftpclient.model.item.impl.TransferItem;
import com.diogoandlucas.ftpclient.view.components.RoundedButton;
import com.diogoandlucas.ftpclient.view.components.RoundedPasswordField;
import com.diogoandlucas.ftpclient.view.components.RoundedTextField;
import com.diogoandlucas.ftpclient.view.panel.file.table.FileTable;
import com.diogoandlucas.ftpclient.view.panel.tranfer.table.TransferTable;
import com.diogoandlucas.ftpclient.view.popup.Popup;
import com.diogoandlucas.ftpclient.view.popup.PopupBuilder;
import com.diogoandlucas.ftpclient.view.popup.item.PopupItem;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ViewUtil{

    public static JLabel createLabel(String text, Color foreground){
        JLabel label = new JLabel(text);
        label.setForeground(foreground);
        return label;
    }

    public static JTextField createTextField(int columns, Color background, Color foreground, boolean password){
        JTextField textField = password ? new RoundedPasswordField(columns) : new RoundedTextField(columns);
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

    public static JDialog createInputDialog(JFrame frame, String text, String title, Consumer<String>response, String initialText){
        JDialog dialog = new JDialog(frame, title);
        dialog.setLayout(new BorderLayout(10, 0));
        dialog.setSize(300, 150);

        JLabel label = createLabel(text, ColorConstants.LABEL);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dialog.add(label, BorderLayout.NORTH);

        JTextField inputField = createTextField(20, ColorConstants.FIELD, ColorConstants.LABEL, false);
        inputField.setText(initialText);
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5, 0));
        centerPanel.setBackground(ColorConstants.BACKGROUND);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        centerPanel.add(inputField);

        JButton okButton = createButton("   Ok   ", ColorConstants.FIELD, ColorConstants.LABEL, ColorConstants.HOVER_BACKGROUND, ColorConstants.CLICK_BACKGROUND);
        okButton.addActionListener(event -> {
            response.accept(inputField.getText());
            dialog.dispose();
        });
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

    public static Popup createPopupServerTable(JFrame frame, FileTable fileTable, TransferTable transferTable, FTPController controller, String currentDirectory){

        return PopupBuilder
                .create()
                .setComponent(fileTable)
                .addItem(new PopupItem("Transferir", e -> {

                    Item item = fileTable.getItem(fileTable.getSelectedRow());
                    TransferItem transferItem = new TransferItem(item, transferTable, TransferItem.Status.DOWNLOAD);
                    transferTable.addItem(transferItem);

                    controller.downloadFile(item.getName(), "/home/diogo/Desktop/", transferItem)
                            .thenAccept(_ -> transferTable.removeItem(transferItem))
                            .exceptionally(_ -> {
                                SwingUtilities.invokeLater(() -> createWarningDialog(frame, "<html>Ocorreu um erro ao transferir o ficheiro.</html>", "Erro"));
                                return null;
                            });

                }))
                .addSeparator()
                .addItem(new PopupItem("Criar Pasta", e -> {

                    createInputDialog(frame, "<html>Insira o nome da pasta a ser criada: </html>", "Criar pasta", response -> {

                        controller.makeDirectory(response)
                                .thenCombine(controller.getItems(), (_, items) -> {
                                    fileTable.setItems(items);
                                    return null;
                                })
                                .exceptionally((_ -> {
                                    SwingUtilities.invokeLater(() -> createWarningDialog(frame, "<html>Ocorreu um erro ao criar a pasta.</html>", "Erro"));
                                    return null;
                                }));

                    }, currentDirectory);

                }))
                .addItem(new PopupItem("Criar Ficheiro", e -> {

                    createInputDialog(frame, "<html>Insira o nome do ficheiro a ser criado: </html>", "Criar ficheiro", response -> {

                        controller.makeFile(response)
                                .thenCombine(controller.getItems(), (_, items) -> {
                                    fileTable.setItems(items);
                                    return null;
                                })
                                .exceptionally(_ -> {
                                    SwingUtilities.invokeLater(() -> createWarningDialog(frame, "<html>Ocorreu um erro ao criar o ficheiro.</html>", "Erro"));
                                    return null;
                                });

                    },"");

                }))
                .addItem(new PopupItem("Atualizar", e -> {

                    controller.getItems()
                            .thenAccept(fileTable::setItems)
                            .exceptionally(_ -> {
                                SwingUtilities.invokeLater(() -> createWarningDialog(frame, "<html>Ocorreu um erro ao atualizar os itens.</html>", "Erro"));
                                return null;
                            });

                }))
                .addSeparator()
                .addItem(new PopupItem("Eliminar", e -> {

                    Item item = fileTable.getItem(fileTable.getSelectedRow());

                    CompletableFuture<Void> cf = item instanceof FileItem ? controller.removeFile(item.getName()) : controller.removeDirectory(item.getName());

                    cf
                            .thenCombine(controller.getItems(), (_, items) -> {
                                fileTable.setItems(items);
                                return null;
                            })
                            .exceptionally(_ -> {
                                SwingUtilities.invokeLater(() -> createWarningDialog(frame, "<html>Ocorreu um erro ao tentar eliminar o item.</html>", "Erro"));
                                return null;
                            });

                }))
                .addItem(new PopupItem("Mudar o nome", e -> {
                    String filename = fileTable.getItem(fileTable.getSelectedRow()).getName();
                    createInputDialog(frame, "<html>Insira o nome que deseja no item: </html>", "Renomear item", response -> {

                        controller.renameItem(filename, response)
                                .thenCombine(controller.getItems(), (_, items) -> {
                                    fileTable.setItems(items);
                                    return null;
                                })
                                .exceptionally(_ -> {
                                    SwingUtilities.invokeLater(() -> createWarningDialog(frame, "<html>Ocorreu um erro ao renomear o item.</html>", "Erro"));
                                    return null;
                                });

                    },filename);

                }))
                .build();
    }

    public static Popup createPopupServerScrollpane(JFrame frame, JScrollPane scrollPane, FileTable fileTable, FTPController controller, String currentDirectory){

        return PopupBuilder
                .create()
                .setComponent(scrollPane)
                .addItem(new PopupItem("Transferir", e -> {}, popupItem -> popupItem.setEnabled(false)))
                .addSeparator()
                .addItem(new PopupItem("Criar Pasta", e -> {

                    createInputDialog(frame, "<html>Insira o nome da pasta a ser criada: </html>", "Criar pasta", response -> {

                        controller.makeDirectory(response)
                                .thenCombine(controller.getItems(), (_, items) -> {
                                    fileTable.setItems(items);
                                    return null;
                                })
                                .exceptionally((_ -> {
                                    SwingUtilities.invokeLater(() -> createWarningDialog(frame, "<html>Ocorreu um erro ao criar a pasta.</html>", "Erro"));
                                    return null;
                                }));

                    }, currentDirectory);

                }))
                .addItem(new PopupItem("Criar Ficheiro", e -> {

                    createInputDialog(frame, "<html>Insira o nome do ficheiro a ser criado: </html>", "Criar ficheiro", response -> {

                        System.out.println(response);

                        controller.makeFile(response)
                                .thenCombine(controller.getItems(), (_, items) -> {
                                    fileTable.setItems(items);
                                    return null;
                                })
                                .exceptionally(_ -> {
                                    SwingUtilities.invokeLater(() -> createWarningDialog(frame, "<html>Ocorreu um erro ao criar o ficheiro.</html>", "Erro"));
                                    return null;
                                });

                    },"");

                }))
                .addItem(new PopupItem("Atualizar", e -> {

                    controller.getItems()
                            .thenAccept(fileTable::setItems)
                            .exceptionally(_ -> {
                                SwingUtilities.invokeLater(() -> createWarningDialog(frame, "<html>Ocorreu um erro ao atualizar os itens.</html>", "Erro"));
                                return null;
                            });

                }))
                .addSeparator()
                .addItem(new PopupItem("Eliminar", e -> {}, popupItem -> popupItem.setEnabled(false)))
                .addItem(new PopupItem("Mudar o nome", e -> {}, popupItem -> popupItem.setEnabled(false)))
                .build();
    }


}

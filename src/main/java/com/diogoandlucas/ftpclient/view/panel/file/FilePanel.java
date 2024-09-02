package com.diogoandlucas.ftpclient.view.panel.file;

import com.diogoandlucas.ftpclient.model.item.Item;
import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.view.panel.file.table.FileTable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FilePanel extends JPanel {

    private final FileTable table = new FileTable();

    public FilePanel(String name) {

        this.setLayout(new BorderLayout(0, 10));
        this.setBackground(ColorConstants.BACKGROUND);
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.add(new AddressPanel(name), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(ColorConstants.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorConstants.LABEL));

        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void setItems(List<Item> items) {
        table.setItems(items);
    }

    public FileTable getTable() {
        return table;
    }
}

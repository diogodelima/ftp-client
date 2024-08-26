package com.diogoandlucas.ftpclient.view.panel.file.table;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.model.item.Item;

import javax.swing.*;
import java.util.List;

public class FileTable extends JTable {

    public FileTable(List<Item> items) {
        super(new FileTableModel(items));

        this.setBackground(ColorConstants.BACKGROUND);
        this.setForeground(ColorConstants.LABEL);
        this.getTableHeader().setBackground(ColorConstants.BACKGROUND);
        this.getTableHeader().setForeground(ColorConstants.LABEL);
    }

}
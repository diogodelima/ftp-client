package com.diogoandlucas.ftpclient.view.panel.file.table;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.model.item.Item;
import com.diogoandlucas.ftpclient.view.util.ViewUtil;

import javax.swing.*;
import java.util.List;

public class FileTable extends JTable {

    public FileTable() {
        super(new FileTableModel());

        this.setBackground(ColorConstants.BACKGROUND);
        this.setForeground(ColorConstants.LABEL);
        this.setGridColor(ColorConstants.BACKGROUND);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.getTableHeader().setBackground(ColorConstants.BACKGROUND);
        this.getTableHeader().setForeground(ColorConstants.LABEL);
        this.getTableHeader().setBorder(BorderFactory.createEmptyBorder());

    }

    public void setItems(List<Item> items) {
        FileTableModel model = (FileTableModel) this.getModel();
        model.setItems(items);
        revalidate();
    }

    public Item getItem(int rowIndex){
        FileTableModel model = (FileTableModel) this.getModel();
        return model.getItem(rowIndex);
    }

}

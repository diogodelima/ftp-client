package com.diogoandlucas.ftpclient.view.panel.file.table;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.model.item.Item;
import com.diogoandlucas.ftpclient.view.util.DefaultTable;
import com.diogoandlucas.ftpclient.view.util.ViewUtil;

import javax.swing.*;
import java.util.List;

public class FileTable extends DefaultTable {

    public FileTable() {
        super(new FileTableModel());
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

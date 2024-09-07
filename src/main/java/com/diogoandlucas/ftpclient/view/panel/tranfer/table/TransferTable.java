package com.diogoandlucas.ftpclient.view.panel.tranfer.table;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.model.item.impl.TransferItem;
import com.diogoandlucas.ftpclient.view.panel.tranfer.icon.TransferBarRenderer;
import com.diogoandlucas.ftpclient.view.util.DefaultTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TransferTable extends DefaultTable {

    public TransferTable() {
        super(new TransferTableModel());

        this.setRowHeight(30);
        this.getColumnModel().getColumn(3).setCellRenderer(new TransferBarRenderer());
    }

    public void addItem(TransferItem item){
        TransferTableModel model = (TransferTableModel) getModel();
        model.addItem(item);
        revalidate();
    }

    public void removeItem(TransferItem item){
        TransferTableModel model = (TransferTableModel) getModel();
        model.removeItem(item);
        revalidate();
    }

}

package com.diogoandlucas.ftpclient.view.panel.tranfer.table;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.model.item.impl.TransferItem;
import com.diogoandlucas.ftpclient.view.panel.tranfer.icon.TransferBarRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TransferTable extends JTable{

    public TransferTable() {
        super(new TransferTableModel());

        this.setBackground(ColorConstants.BACKGROUND);
        this.setForeground(ColorConstants.LABEL);
        this.setGridColor(ColorConstants.BACKGROUND);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.getTableHeader().setBackground(ColorConstants.BACKGROUND);
        this.getTableHeader().setForeground(ColorConstants.LABEL);
        this.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        this.setRowHeight(30);
        this.getColumnModel().getColumn(3).setCellRenderer(new TransferBarRenderer());

        for (int i = 0; i < this.getColumnCount() - 1; i++) {
            DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
            tableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tableCellRenderer.setVerticalAlignment(SwingConstants.CENTER);
            this.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);
        }

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

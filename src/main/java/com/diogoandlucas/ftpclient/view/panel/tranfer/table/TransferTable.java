package com.diogoandlucas.ftpclient.view.panel.tranfer.table;

import com.diogoandlucas.ftpclient.constants.ColorConstants;

import javax.swing.*;

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
    }

}

package com.diogoandlucas.ftpclient.view.panel.tranfer;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.view.panel.tranfer.table.TransferTable;

import javax.swing.*;
import java.awt.*;

public class TransferPanel extends JPanel {

    public TransferPanel() {

        this.setLayout(new BorderLayout(0, 10));
        this.setBackground(ColorConstants.BACKGROUND);
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        TransferTable table = new TransferTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(ColorConstants.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorConstants.LABEL));

        this.add(scrollPane, BorderLayout.CENTER);
    }

}

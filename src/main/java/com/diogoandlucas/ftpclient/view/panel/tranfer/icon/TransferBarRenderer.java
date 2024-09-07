package com.diogoandlucas.ftpclient.view.panel.tranfer.icon;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TransferBarRenderer extends DefaultTableCellRenderer {

    public TransferBarRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (value instanceof TransferBar transferBar) {

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setOpaque(false);
            panel.add(transferBar);

            return panel;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

    }

}

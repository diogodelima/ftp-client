package com.diogoandlucas.ftpclient.view.util;

import com.diogoandlucas.ftpclient.constants.ColorConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

public class DefaultTable extends JTable {

    public DefaultTable(TableModel tableModel) {

        super(tableModel);

        this.setBackground(ColorConstants.BACKGROUND);
        this.setForeground(ColorConstants.LABEL);
        this.setGridColor(ColorConstants.BACKGROUND);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.getTableHeader().setBackground(ColorConstants.BACKGROUND);
        this.getTableHeader().setForeground(ColorConstants.LABEL);
        this.getTableHeader().setBorder(BorderFactory.createEmptyBorder());

        TableCellRenderer tableCellRenderer = new TableCellRenderer();

        for (int i = 0; i < this.getColumnCount(); i++)
            this.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);

    }

    private static class TableCellRenderer extends DefaultTableCellRenderer {

        private TableCellRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(ColorConstants.BACKGROUND);
            panel.add(component);

            if (column == 0)
                panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

            if (column == table.getColumnCount() - 1)
                panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

            return panel;
        }

    }

}

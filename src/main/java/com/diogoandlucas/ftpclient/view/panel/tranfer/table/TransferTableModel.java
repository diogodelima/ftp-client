package com.diogoandlucas.ftpclient.view.panel.tranfer.table;

import com.diogoandlucas.ftpclient.model.item.Item;
import com.diogoandlucas.ftpclient.model.item.impl.FileItem;
import com.diogoandlucas.ftpclient.model.item.impl.TransferItem;
import com.diogoandlucas.ftpclient.view.panel.tranfer.icon.TransferBar;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class TransferTableModel implements TableModel {

    private final List<TransferItem> items = new ArrayList<>();

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {

        return switch (columnIndex) {
            case 0 -> "Ficheiro";
            case 1 -> "Tamanho";
            case 2 -> "Estado";
            case 3 -> "Progresso";
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 3 -> TransferBar.class;
            default -> String.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        final TransferItem item = this.items.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> item.getName();
            case 1 -> item.getSize();
            case 2 -> item.getStatus().name();
            case 3 -> item.getTransferBar();
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if (!(aValue instanceof TransferItem))
            throw new IllegalStateException("Unexpected value: " + aValue);

        this.items.set(rowIndex, (TransferItem) aValue);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    public void addItem(TransferItem item){
        items.add(item);
    }

    public void removeItem(TransferItem item){
        items.remove(item);
    }

}

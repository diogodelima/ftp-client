package com.diogoandlucas.ftpclient.view.panel.file.table;

import com.diogoandlucas.ftpclient.model.item.Item;
import com.diogoandlucas.ftpclient.model.item.impl.FileItem;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class FileTableModel implements TableModel {

    private final List<Item> items;

    public FileTableModel(List<Item> items) {
        this.items = items;
    }

    @Override
    public int getRowCount() {
        return this.items.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {

        return switch (columnIndex) {
            case 0 -> "Nome";
            case 1 -> "Tamanho";
            case 2 -> "Tipo";
            case 3 -> "Modificado";
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        final Item item = this.items.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> item.getName();
            case 1 -> item.getSize();
            case 2 -> (item instanceof FileItem) ? "Ficheiro" : "Pasta de Ficheiros";
            case 3 -> item.getLastModify().toString();
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if (!(aValue instanceof Item))
            throw new IllegalStateException("Unexpected value: " + aValue);

        this.items.set(rowIndex, (Item) aValue);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

}

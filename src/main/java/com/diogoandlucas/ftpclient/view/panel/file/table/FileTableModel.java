package com.diogoandlucas.ftpclient.view.panel.file.table;

import com.diogoandlucas.ftpclient.model.item.Item;
import com.diogoandlucas.ftpclient.model.item.impl.FileItem;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileTableModel extends AbstractTableModel {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:s");

    private List<Item> items = new ArrayList<>();

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
            case 3 -> formatter.format(item.getLastModify());
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

    public void setItems(List<Item> items) {
        this.items = items;
    }

}

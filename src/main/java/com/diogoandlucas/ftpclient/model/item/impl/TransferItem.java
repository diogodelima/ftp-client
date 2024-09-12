package com.diogoandlucas.ftpclient.model.item.impl;

import com.diogoandlucas.ftpclient.model.item.Item;
import com.diogoandlucas.ftpclient.model.observer.Observer;
import com.diogoandlucas.ftpclient.view.panel.tranfer.icon.TransferBar;
import com.diogoandlucas.ftpclient.view.panel.tranfer.table.TransferTable;

import javax.swing.*;

public class TransferItem extends BaseItem implements Observer {

    private final TransferBar transferBar = new TransferBar();
    private final TransferTable transferTable;
    private final Status status;

    public TransferItem(Item item, TransferTable transferTable, Status status) {
        super(item.getName(), item.getLastModify(), item.getSize());
        this.transferTable = transferTable;
        this.status = status;
    }

    public TransferBar getTransferBar() {
        return transferBar;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public void update(double byteRate, long elapsedTime, int bytesRead, long totalBytesToRead) {
        double percentage = (double) bytesRead / (double) totalBytesToRead;
        SwingUtilities.invokeLater(() -> {
            transferBar.setPercentage(percentage);
            transferBar.setElapsedTime(elapsedTime);
            transferTable.repaint();
        });
    }

    public enum Status {

        DOWNLOAD,
        UPLOAD

    }

}

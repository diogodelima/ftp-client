package com.diogoandlucas.ftpclient.model.item.impl;

import com.diogoandlucas.ftpclient.model.item.Item;
import com.diogoandlucas.ftpclient.model.observer.Observer;
import com.diogoandlucas.ftpclient.view.panel.tranfer.icon.TransferBar;

public class TransferItem extends BaseItem implements Observer {

    private final TransferBar transferBar;
    private final Status status;

    public TransferItem(Item item, TransferBar transferBar, Status status) {
        super(item.getName(), item.getLastModify(), item.getSize());
        this.transferBar = transferBar;
        this.status = status;
    }

    public TransferBar getTransferBar() {
        return transferBar;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public void update(double byteRate, long elapsedTime, int bytesRead, int totalBytesToRead) {
        this.transferBar.update(byteRate, elapsedTime, bytesRead, totalBytesToRead);
    }

    public enum Status {

        DOWNLOAD,
        UPLOAD

    }

}

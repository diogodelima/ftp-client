package com.diogoandlucas.ftpclient.model.item.impl;

import java.time.LocalDateTime;

public class DirectoryItem extends BaseItem {

    public DirectoryItem(String name, LocalDateTime lastModify, long size) {
        super(name, lastModify, size);
    }

}

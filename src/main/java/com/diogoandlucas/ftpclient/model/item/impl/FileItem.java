package com.diogoandlucas.ftpclient.model.item.impl;

import java.time.LocalDateTime;

public class FileItem extends BaseItem {

    public FileItem(String name, LocalDateTime lastModify, long size) {
        super(name, lastModify, size);
    }

}

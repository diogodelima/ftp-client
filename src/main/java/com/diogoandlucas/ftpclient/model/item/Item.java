package com.diogoandlucas.ftpclient.model.item;

import java.time.LocalDateTime;

public interface Item {

    String getName();

    LocalDateTime getLastModify();

    long getSize();

}

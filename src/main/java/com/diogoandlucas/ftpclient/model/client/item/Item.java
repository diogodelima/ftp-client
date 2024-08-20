package com.diogoandlucas.ftpclient.model.client.item;

import java.time.LocalDateTime;

public interface Item {

    String getName();

    LocalDateTime getLastModify();

    long getSize();

}

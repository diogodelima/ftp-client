package com.diogoandlucas.ftpclient.model.item.impl;

import com.diogoandlucas.ftpclient.model.item.Item;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class BaseItem implements Item {

    private final String name;
    private final LocalDateTime lastModify;
    private final long size;

    public BaseItem(String name, LocalDateTime lastModify, long size) {
        this.name = name;
        this.lastModify = lastModify;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDateTime getLastModify() {
        return lastModify;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return String.format(
                """
                {
                  "name": "%s",
                  "lastModify": "%s",
                  "size": "%s"
                }
                """,
                this.name, this.lastModify.toString(), this.size
        );
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || this.getClass() != obj.getClass())
            return false;

        BaseItem other = (BaseItem) obj;
        return this.name.equals(other.name) && this.lastModify.equals(other.lastModify) && this.size == other.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.lastModify, this.size);
    }

}

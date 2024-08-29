package com.diogoandlucas.ftpclient.model.builder;

public abstract class Builder<T> {

    private final T t;

    public Builder(T t) {
        this.t = t;
    }

    protected T getObject() {
        return t;
    }

    public abstract T build();

}

package com.diogoandlucas.ftpclient.view.popup;

import com.diogoandlucas.ftpclient.model.builder.Builder;
import com.diogoandlucas.ftpclient.view.popup.item.PopupItem;

import javax.swing.*;

public class PopupBuilder extends Builder<Popup> {


    private PopupBuilder(Popup popup) {
        super(popup);
    }

    @Override
    public Popup build() {
        return getObject();
    }

    public PopupBuilder addSeparator(){
        this.getObject().addSeparator();
        return this;
    }

    public PopupBuilder addItem(PopupItem item){
        this.getObject().add(item);
        return this;
    }

    public PopupBuilder setComponent(JComponent component) {
        this.getObject().setPanel(component);
        return this;
    }

    public static PopupBuilder create(){
        return new PopupBuilder(new Popup());
    }

}

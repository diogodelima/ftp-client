package com.diogoandlucas.ftpclient.view.popup.item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class PopupItem extends JMenuItem{

    public PopupItem (String text, Consumer<ActionEvent> eventOnClick, Consumer<PopupItem> eventOnCreate){

        super(text);
        eventOnCreate.accept(this);
        this.addActionListener(eventOnClick::accept);

    }

    public PopupItem (String text, Consumer<ActionEvent> eventOnClick){

        this(text, eventOnClick, ignored -> {});

    }

}





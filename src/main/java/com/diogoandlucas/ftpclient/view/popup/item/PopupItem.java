package com.diogoandlucas.ftpclient.view.popup.item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class PopupItem extends JMenuItem{

    public PopupItem (String text, Consumer<ActionEvent> event){

        super(text);
        this.addActionListener(event::accept);

    }

}

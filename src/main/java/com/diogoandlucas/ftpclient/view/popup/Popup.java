package com.diogoandlucas.ftpclient.view.popup;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Popup extends JPopupMenu {

    public void setPanel(JComponent component){
        component.addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    Popup.this.show(e.getComponent(), e.getX(), e.getY());
                }
            }

        });
    }

}

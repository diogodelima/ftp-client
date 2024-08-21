package com.diogoandlucas.ftpclient.view.panel.file;

import com.diogoandlucas.ftpclient.constants.ColorConstants;

import javax.swing.*;
import java.awt.*;

public class FilePanel extends JPanel {

    public FilePanel(String name) {

        this.setLayout(new BorderLayout());
        this.setBackground(ColorConstants.BACKGROUND);
        this.add(new AddressPanel(name), BorderLayout.NORTH);

    }

}

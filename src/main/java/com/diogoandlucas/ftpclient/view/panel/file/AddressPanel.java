package com.diogoandlucas.ftpclient.view.panel.file;

import com.diogoandlucas.ftpclient.constants.ColorConstants;

import javax.swing.*;
import java.awt.*;

import static com.diogoandlucas.ftpclient.view.util.ViewUtil.*;

public class AddressPanel extends JPanel {

    public AddressPanel(String name) {

        this.setLayout(new BorderLayout(5, 5));
        this.setBackground(ColorConstants.BACKGROUND);

        JLabel addressLabel = createLabel(name, ColorConstants.LABEL);
        JTextField addressField = createTextField(0, ColorConstants.FIELD, ColorConstants.LABEL, false);

        this.add(addressLabel, BorderLayout.WEST);
        this.add(addressField, BorderLayout.CENTER);
    }

}

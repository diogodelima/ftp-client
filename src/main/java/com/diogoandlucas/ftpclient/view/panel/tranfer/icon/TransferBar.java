package com.diogoandlucas.ftpclient.view.panel.tranfer.icon;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.view.components.DualColorLabel;

import javax.swing.*;
import java.awt.*;

public class TransferBar extends JPanel {

    private final DualColorLabel percentageLabel = new DualColorLabel("0%");
    private final JLabel elapsedTimeLabel = new JLabel("0");

    public TransferBar() {

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        setBackground(ColorConstants.BACKGROUND);

        elapsedTimeLabel.setForeground(ColorConstants.LABEL);

        this.add(percentageLabel);
        this.add(elapsedTimeLabel);
    }

    public void setPercentage(double percentage) {
        this.percentageLabel.setPercentage(percentage);
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTimeLabel.setText(String.valueOf(elapsedTime));
    }

}

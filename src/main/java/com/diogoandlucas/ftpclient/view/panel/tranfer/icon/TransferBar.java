package com.diogoandlucas.ftpclient.view.panel.tranfer.icon;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.model.observer.Observer;
import com.diogoandlucas.ftpclient.view.components.DualColorLabel;

import javax.swing.*;
import java.awt.*;

public class TransferBar extends JPanel implements Observer {

    private final DualColorLabel percentageLabel;

    public TransferBar() {

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        setBackground(ColorConstants.BACKGROUND);

        this.percentageLabel = new DualColorLabel("0%");
        this.add(percentageLabel);

    }

    @Override
    public void update(double byteRate, long elapsedTime, int bytesRead, int totalBytesToRead) {

        double percentage = ((double) bytesRead / (double) totalBytesToRead);
        this.percentageLabel.setText(String.format("%.2f", percentage * 100) + "%");
        this.percentageLabel.setPercentage(percentage);

    }

}

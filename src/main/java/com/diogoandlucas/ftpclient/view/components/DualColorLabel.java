package com.diogoandlucas.ftpclient.view.components;

import com.diogoandlucas.ftpclient.constants.ColorConstants;

import javax.swing.*;
import java.awt.*;

public class DualColorLabel extends JLabel {

    private double percentage = 0.0;

    public DualColorLabel(String text) {
        super(text);
        setPercentage(0);
        setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        setPreferredSize(new Dimension(100, 20));
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        int maxWidth = (int) (width * percentage);

        g2d.setColor(ColorConstants.TRANSFER_BAR_PROGRESS);
        g2d.fillRect(0, 0, maxWidth, height);

        g2d.setColor(ColorConstants.TRANSFER_BAR_BACKGROUND);
        g2d.fillRect(maxWidth, 0, width - maxWidth, height);

        super.paintComponent(g);
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
        System.out.println(percentage);
        this.setText(String.format("%.2f%%", percentage * 100));
        repaint();
        revalidate();
    }

}

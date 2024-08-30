package com.diogoandlucas.ftpclient.view.components;

import javax.swing.*;
import java.awt.*;

public class DualColorLabel extends JLabel {

    private double percentage = 0.0;

    public DualColorLabel(String text) {
        super(text);
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        int maxWidth = (int) (width * percentage);

        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, maxWidth, height);

        g2d.setColor(Color.BLUE);
        g2d.fillRect(maxWidth, 0, width - maxWidth, height);

        g2d.setColor(Color.WHITE);
        g2d.drawString(getText(), 10, height / 2 + g2d.getFontMetrics().getAscent() / 2);
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
        this.repaint();
    }

}

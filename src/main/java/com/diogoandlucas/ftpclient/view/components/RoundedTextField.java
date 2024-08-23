package com.diogoandlucas.ftpclient.view.components;

import javax.swing.*;
import java.awt.*;

public class RoundedTextField extends JTextField {

    public RoundedTextField(int columns) {
        this.setColumns(columns);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setPaint(getBackground());
        g2.fill(((RoundedBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    public void updateUI() {
        super.updateUI();
        setOpaque(false);
        setBorder(new RoundedBorder());
    }

}

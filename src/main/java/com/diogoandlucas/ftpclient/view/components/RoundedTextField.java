package com.diogoandlucas.ftpclient.view.components;

import com.diogoandlucas.ftpclient.constants.ColorConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RoundedTextField extends JTextField {

    public RoundedTextField(int columns) {
        this.setColumns(columns);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                if (getBorder() instanceof RoundedBorder border) {
                    border.setColor(ColorConstants.LABEL);
                    repaint();
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getBorder() instanceof RoundedBorder border) {
                    border.setColor(null);
                    repaint();
                }
            }
        });

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

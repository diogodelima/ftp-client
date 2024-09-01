package com.diogoandlucas.ftpclient.view.components;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private Color clickBackground;
    private Color hoverBackground;

    public RoundedButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setBorder(new RoundedBorder());
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        if (getModel().isPressed() && getClickBackground() != null) {
            g2.setPaint(getClickBackground());
        }else if (getModel().isRollover() && getHoverBackground() != null) {
            g2.setPaint(getHoverBackground());
        }else {
            g2.setPaint(getBackground());
        }

        g2.fill(((RoundedBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    public void updateUI() {
        super.updateUI();
        setFocusPainted(false);
        setOpaque(false);
        setBorder(new RoundedBorder());
    }

    public Color getClickBackground() {
        return clickBackground;
    }

    public void setClickBackground(Color clickBackground) {
        this.clickBackground = clickBackground;
    }

    public Color getHoverBackground() {
        return hoverBackground;
    }

    public void setHoverBackground(Color hoverBackground) {
        this.hoverBackground = hoverBackground;
    }

}

package com.diogoandlucas.ftpclient.view.components;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class RoundedBorder extends AbstractBorder {

    private static final Color ALPHA_ZERO = new Color(0x0, true);
    private Color color;

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape border = getBorderShape(x, y, width - 1, height - 1);
        g2.setPaint(ALPHA_ZERO);
        Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
        corner.subtract(new Area(border));
        g2.fill(corner);
        g2.setPaint(Color.GRAY);
        g2.setColor(color);
        g2.draw(border);
        g2.dispose();
    }

    public Shape getBorderShape(int x, int y, int w, int h) {
        return new RoundRectangle2D.Double(x, y, w, h, h, h);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(4, 8, 4, 8);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.set(4, 8, 4, 8);
        return insets;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

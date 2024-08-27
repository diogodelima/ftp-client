package com.diogoandlucas.ftpclient.view;

import com.diogoandlucas.ftpclient.constants.ColorConstants;
import com.diogoandlucas.ftpclient.controller.FTPController;
import com.diogoandlucas.ftpclient.model.item.Item;
import com.diogoandlucas.ftpclient.model.item.impl.DirectoryItem;
import com.diogoandlucas.ftpclient.model.item.impl.FileItem;
import com.diogoandlucas.ftpclient.view.panel.CredentialsPanel;
import com.diogoandlucas.ftpclient.view.panel.file.FilePanel;
import com.diogoandlucas.ftpclient.view.panel.tranfer.TransferPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDateTime;
import java.util.List;

public class View extends JFrame {

    private final FTPController ftpController = new FTPController();

    public View(int width, int height){

        this.setSize(width, height);
        this.setLayout(new BorderLayout(0, 10));
        this.getContentPane().setBackground(ColorConstants.BACKGROUND);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(new CredentialsPanel(ftpController, this), BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setBackground(ColorConstants.BACKGROUND);
        this.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(1, 2, 10, 5));
        
        List<Item> items = List.of(
                new FileItem("teste.txt", LocalDateTime.now(), 1000),
                new FileItem("hack.sh", LocalDateTime.now().minusDays(54), 412),
                new DirectoryItem("pasta", LocalDateTime.now().plusDays(3), 12312421)
        );
        
        panel.add(new FilePanel("Endereço local:", items));
        panel.add(new FilePanel("Endereço remoto:", List.of()));

        JPanel transferPanel = new TransferPanel();
        transferPanel.setPreferredSize(new Dimension(width, (int) (height*0.2)));
        this.add(transferPanel, BorderLayout.SOUTH);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int height = (int) (View.this.getHeight()*0.2);
                int width = View.this.getWidth();
                transferPanel.setPreferredSize(new Dimension(width, height));
                View.this.revalidate();
                View.this.repaint();
            }
        });

        this.setVisible(true);
    }

}

package dev.skyit.pao.ui;

import dev.skyit.pao.client.Client;

import javax.swing.*;

public class ClientDetailsPage extends JFrame {
    private JLabel selectedClientLabel;
    private JPanel panel;
    private final Client client;

    public ClientDetailsPage(Client client) {
        super();
        this.client = client;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(600, 600);

        selectedClientLabel.setText(client.getAlias());
    }
}

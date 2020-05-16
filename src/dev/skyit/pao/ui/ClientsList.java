package dev.skyit.pao.ui;

import dev.skyit.pao.api.Bank;
import dev.skyit.pao.client.Client;

import javax.swing.*;
import java.awt.*;

public class ClientsList extends JFrame {
    private JPanel mainPanel;
    private JList<Client> clientsJList;
    private JLabel selectLabel;


    public ClientsList(String appName) {
        super(appName);
        var api = Bank.getInstance();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(800, 800);

        selectLabel.setBackground(Color.CYAN);

        DefaultListModel<Client> clientsModel = new DefaultListModel<>();
        api.getAllClients().forEach(clientsModel::addElement);
        clientsJList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            var label = new JLabel(value.getAlias(), SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.PLAIN, 20));
            if (isSelected){
                label.setForeground(Color.BLUE);
            }
            return label;
        });
        clientsJList.setModel(clientsModel);
        clientsJList.addListSelectionListener(e -> {
            showClientDetails(clientsJList.getSelectedValue());
        });
        clientsJList.setSelectionBackground(Color.BLUE);


    }

    private void showClientDetails(Client client) {
        var detailsFrame = new ClientDetailsPage(client);
        detailsFrame.setVisible(true);
        setVisible(false);
    }
}

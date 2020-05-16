package dev.skyit.pao.ui;

import dev.skyit.pao.api.Bank;
import dev.skyit.pao.client.BalanceAccount;
import dev.skyit.pao.client.Client;
import dev.skyit.pao.client.transfers.CompanyTransfer;
import dev.skyit.pao.client.transfers.Transfer;
import dev.skyit.pao.exceptions.TransferException;
import dev.skyit.pao.utility.Currency;
import dev.skyit.pao.utility.CurrencyModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//TODO poor work flow, should introduce RxJava
public class ClientDetailsPage extends JFrame {
    private JLabel selectedClientLabel;
    private JPanel panel;
    private JList<BalanceAccount> clientAccountsList;
    private JList<CurrencyModel> currencyList;
    private JList<Transfer> clientTransfersList;
    private JButton makeTransferButton;
    private JButton goBackButton;
    private final Client client;

    private final Bank api = Bank.getInstance();

    public ClientDetailsPage(Client client) {
        super();
        this.client = client;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(1000, 800);

        selectedClientLabel.setText(client.getAlias());
        setCurrencyTable();
        setupBalanceAccountsList();
        setupTransfersTable();
        makeTransferButton.addActionListener(e -> singleDialogInformation());
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // it doesn't actually go back, just opens another frame
                var clientsListFrame = new ClientsList("My app");
                clientsListFrame.setVisible(true);
                setVisible(false);
            }
        });
    }

    private void setupTransfersTable() {
        var transfers = api.getAllTransfers(client.getId());
        var transfersModel = new DefaultListModel<Transfer>();
        transfersModel.addAll(transfers);
        clientTransfersList.setCellRenderer(new ListCellRenderer<Transfer>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Transfer> list, Transfer value, int index, boolean isSelected, boolean cellHasFocus) {
                var label = new JLabel();
                String extra = "";
                if (value instanceof CompanyTransfer) {
                    extra = String.format("; commision %.2f", ((CompanyTransfer) value).appliedCommision);
                }
                label.setText(
                        String.format( "Transferred %.2f %s from %s to %s with rate %.2f",
                        value.getAmountInSourceCurrency(),
                        value.getSourceCurrencyCode(),
                        value.getSourceCurrencyCode(),
                        value.getDestinationCurrencyCode(),
                        value.getExchangeRate()) + extra
                );
                return label;
            }
        });

        clientTransfersList.setModel(transfersModel);
    }

    private void setupBalanceAccountsList() {
        //we get another reference to the client
        var client = api.getClientById(this.client.getId());
        var balanceAccountsModel = new DefaultListModel<BalanceAccount>();
        balanceAccountsModel.addAll(client.getAccounts());
        clientAccountsList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            var label = new JLabel();
            var currency = api.getCurrencyById(value.getCurrencyId());
            label.setText(String.format(
                    "In Currency %s amount = %.2f",
                    currency.getCode(), value.getAmount()
            ));
            return label;
        });

        clientAccountsList.setModel(balanceAccountsModel);

    }

    private void setCurrencyTable() {
        var currencyListModel = new DefaultListModel<CurrencyModel>();
        currencyListModel.addAll(api.getCurrencyConvertTable());
        currencyList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            var label = new JLabel();
            label.setText(String.format("From (id: %d) %s - to (id: %d) %s with rate %.2f",
                    value.getSourceCurrency().getId(),
                    value.getSourceCurrency().getName(),
                    value.getDestinationCurrency().getId(),
                    value.getDestinationCurrency().getName(),
                    value.getRate()));

            return label;
        });

        currencyList.setModel(currencyListModel);
    }

    public void singleDialogInformation() {
        var pane = new JPanel();
        pane.setLayout(new GridLayout(0, 2, 2, 2));

        var sourceCurrencyField = new JTextField(5);
        var destinationCurrencyField = new JTextField(5);
        var amountField = new JTextField(5);

        pane.add(new JLabel("Source currency id?"));
        pane.add(sourceCurrencyField);

        pane.add(new JLabel("Destination currency id?"));
        pane.add(destinationCurrencyField);

        pane.add(new JLabel("Amount?"));
        pane.add(amountField);

        int option = JOptionPane.showConfirmDialog(this, pane, "Please fill all the fields", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {

            Integer sourceCurrencyId;
            Integer destinationCurrencyId;
            Double amount;
            try {
                sourceCurrencyId = Integer.valueOf(sourceCurrencyField.getText());
                destinationCurrencyId = Integer.valueOf(destinationCurrencyField.getText());
                amount = Double.valueOf(amountField.getText());
            } catch (NumberFormatException ex) {
                //should've added validation for each field in particular, but it's a lot of code to write :)
                JOptionPane.showMessageDialog(this,
                        "Wrong input format", "Failure", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                api.makeTransferForClient(client.getId(), sourceCurrencyId, destinationCurrencyId, amount);

                pane = new JPanel();
                pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

                pane.add(new JLabel("Transfer was successful"));
                refreshPage();
                JOptionPane.showMessageDialog(this, pane);


            } catch (TransferException e) {
                JOptionPane.showMessageDialog(this,
                        e.getLocalizedMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void refreshPage() {
        setCurrencyTable();
        setupBalanceAccountsList();
        setupTransfersTable();
    }
}


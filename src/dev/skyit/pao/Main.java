package dev.skyit.pao;

import dev.skyit.pao.api.Bank;
import dev.skyit.pao.exceptions.TransferException;
import dev.skyit.pao.ui.ClientsList;


public class Main {

    public static void main(String[] args) {
        Bank bk = Bank.getInstance();


        //Making some dummy transfers so the list won't be empty
        bk.getAllClients().forEach(c -> {
            try {
                bk.makeTransferForClient(c.getId(), 0, 1, 10d);
            } catch (TransferException e) {
                e.printStackTrace();
            }
        });

        var clientsPage = new ClientsList("My app");
        clientsPage.setVisible(true);
    }
}

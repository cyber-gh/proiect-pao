package dev.skyit.pao;

import dev.skyit.pao.api.Bank;
import dev.skyit.pao.ui.ClientsList;


public class Main {

    public static void main(String[] args) {
        Bank bk = Bank.getInstance();

        var clientsPage = new ClientsList("My app");
        clientsPage.setVisible(true);
    }
}

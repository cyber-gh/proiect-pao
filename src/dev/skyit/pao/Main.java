package dev.skyit.pao;

import dev.skyit.pao.exceptions.TransferException;

public class Main {

    public static void main(String[] args) {
        Bank bk = Bank.shared;
        bk.addDummyData();

        Client me = bk.getClientById(0);
        try {
            me.makeTransfer(0, 1, 10.0);
            System.out.println("Success: " + me.getLastTransfer());
        } catch (TransferException e) {
            System.out.println("Unable to make transfer: " + e.getLocalizedMessage());
        }

        Client company = bk.getClientById(4);
        try {
            company.makeTransfer(0, 1, 10.0);
            System.out.println("Success: " + company.getLastTransfer());
        } catch (TransferException e) {
            System.out.println("Unable to make transfer: " + e.getLocalizedMessage());
        }
    }
}

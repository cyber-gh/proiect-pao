package dev.skyit.pao;

import dev.skyit.pao.api.Bank;
import dev.skyit.pao.client.Client;
import dev.skyit.pao.client.transfers.Transfer;
import dev.skyit.pao.database.csv.Database;
import dev.skyit.pao.database.sqlite.BankDB;
import dev.skyit.pao.exceptions.TransferException;
import dev.skyit.pao.utility.Currency;


public class Main {

    public static void main(String[] args) {
        Bank bk = Bank.shared;
        bk.loadClients();

//        BankDB.getInstance().getCurrenciesDao().delete(currency);
//        BankDB.getInstance().getCurrenciesDao().insert(currency);
//        BankDB.getInstance().getCurrenciesDao().readAll().forEach(System.out::println);

//        Integer clientId = 0;
//        try {
//            bk.makeTransferForClient(clientId,0, 1, 10.0);
//            System.out.println("Success: " + bk.getLastTransfer(clientId));
//        } catch (TransferException e) {
//            System.out.println("Unable to make transfer: " + e.getLocalizedMessage());
//        }
//
//        Integer companyId = 0;
//        try {
//            bk.makeTransferForClient(companyId,0, 1, 10.0);
//            System.out.println("Success: " + bk.getLastTransfer(companyId));
//        } catch (TransferException e) {
//            System.out.println("Unable to make transfer: " + e.getLocalizedMessage());
//        }
//
//        System.out.println(bk.getTotalBalanceInCurrency(clientId, 0));
//        for (Transfer transfer : bk.getTransfersBiggerThan(companyId,100.0)) {
//            System.out.println(transfer);
//        }
    }
}

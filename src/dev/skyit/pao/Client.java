package dev.skyit.pao;

import dev.skyit.pao.exceptions.MissingExchangeRateException;
import dev.skyit.pao.exceptions.TransferException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract public class Client {
    private final Integer id;
    private String alias;
    private List<Transfer> transfers = new ArrayList<>();
    private List<BalanceAccount> accounts = new ArrayList<>();
    protected final BankIFace bank;

    protected static Integer lastTransactionId = 0;

    public Client(Integer id, String alias, BankIFace bank) {
        this.id = id;
        this.alias = alias;
        this.bank = bank;
    }

    public abstract void makeTransfer(Integer sourceId, Integer destinationId, Double amount) throws TransferException;

    public Transfer getLastTransfer() {
        if (transfers.isEmpty()) return null;
        else return transfers.get(transfers.size() - 1);
    }

    public Integer getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void registerAccount(Integer currencyId) {
        accounts.add(new BalanceAccount(currencyId, 20.0));
    }

    protected void addTransfer(Transfer transfer) {
        transfers.add(transfer);
    }

    protected void modifyAccount(Integer currencyId, Double value) {
        for (BalanceAccount account : accounts) {
            if (account.getCurrencyId().equals(currencyId)) {
                account.modifyAmount(value);
                break;
            }
        }
    }
}

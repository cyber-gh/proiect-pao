package dev.skyit.pao;

import dev.skyit.pao.exceptions.MissingExchangeRateException;
import dev.skyit.pao.exceptions.TransferException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

abstract public class Client {
    private final Integer id;
    private String alias;
    private final List<Transfer> transfers = new ArrayList<>();
    private final List<BalanceAccount> accounts = new ArrayList<>();
    protected final BankIFace bank;

    protected static Integer lastTransactionId = 0;

    public Client(Integer id, String alias, BankIFace bank) {
        this.id = id;
        this.alias = alias;
        this.bank = bank;
    }

    public Double getTotalBalanceInCurrency(Integer currencyId) {
        Double total = 0.0;
        for (BalanceAccount account : accounts) {
            if (account.getCurrencyId().equals(currencyId)) total += account.getAmount();
            else {
                Double rate = bank.getExchangeRate(account.getCurrencyId(), currencyId);
                total += rate * account.getAmount();
            }
        }
        return total;
    }

    public List<Transfer> getTransfersBiggerThan(Double amount) {
        return transfers.stream().filter((acc) -> {
            return acc.amountInSourceCurrency > amount;
        }).collect(Collectors.toList());
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

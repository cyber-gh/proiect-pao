package dev.skyit.pao.api;

import dev.skyit.pao.database.Database;
import dev.skyit.pao.utility.Currency;
import dev.skyit.pao.utility.Pair;
import dev.skyit.pao.client.Client;
import dev.skyit.pao.client.CompanyClient;
import dev.skyit.pao.client.SimpleClient;
import dev.skyit.pao.client.transfers.Transfer;
import dev.skyit.pao.exceptions.TransferException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Bank implements BankIFace, ServiceIFace {

    private List<Currency> currencyList = new ArrayList<>();
    private HashMap<Pair, Double> rates = new HashMap<>();
    private List<Client> clients = new ArrayList<>();

    public static final Bank shared = new Bank();

    private Bank() {
        var currencies = Database.shared.loadCurrencies();
        currencyList.addAll(currencies);
        var lst = Database.shared.loadRates();
        lst.forEach((rate) -> {
            rates.put(new Pair(rate.getSourceId(), rate.getDestinationID()), rate.getRate());
        });
    }

    public void addDummyData(){
        addClient(new SimpleClient(0, "boss", shared));
        addClient(new SimpleClient(1, "secretary", shared));
        addClient(new SimpleClient(2, "manager", shared));

        addClient(new CompanyClient(4, "Sky-IT", 0.05, shared));

        currencyList.forEach((currency -> clients.forEach((client -> client.registerAccount(currency.getId())))));
    }

    @Override
    public Currency getCurrencyById(Integer id) {
        for (Currency currency : currencyList) {
            if (currency.getId().equals(id)) return currency;
        }
        return null;
    }

    @Override
    public Double getExchangeRate(Integer sourceCurrency, Integer destinationCurrency) {
        return rates.get(new Pair(sourceCurrency, destinationCurrency));
    }

    @Override
    public void addClient(Client client){
        clients.add(client);
    }


    @Override
    public Client getClientById(Integer id) {
        for (Client client : clients) {
            if (client.getId().equals(id)) return client;
        }
        return null;
    }

    @Override
    public void registerAccountForClient(Integer clientId, Integer currencyId) {
        getClientById(clientId).registerAccount(currencyId);
    }

    @Override
    public void makeTransferForClient(Integer clientId, Integer sourceId, Integer targetId, Double amount) throws TransferException {
        getClientById(clientId).makeTransfer(sourceId, targetId, amount);
    }

    @Override
    public Transfer getLastTransfer(Integer clientId) {
        return getClientById(clientId).getLastTransfer();
    }

    @Override
    public Double getTotalBalanceInCurrency(Integer clientId, Integer currencyId) {
        return getClientById(clientId).getTotalBalanceInCurrency(currencyId);
    }

    @Override
    public List<Transfer> getTransfersBiggerThan(Integer clientId, Double amount) {
        return getClientById(clientId).getTransfersBiggerThan(amount);
    }

    @Override
    public void removeClient(Integer clientId) {
        Iterator it = clients.iterator();
        while (it.hasNext()) {
            Integer id = ((Client) it.next()).getId();
            if (id.equals(clientId)) it.remove();
        }
    }
}

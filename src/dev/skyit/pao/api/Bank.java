package dev.skyit.pao.api;

import dev.skyit.pao.audit.Auditor;
import dev.skyit.pao.database.csv.Database;
import dev.skyit.pao.database.sqlite.BankDB;
import dev.skyit.pao.utility.Currency;
import dev.skyit.pao.utility.CurrencyModel;
import dev.skyit.pao.utility.Pair;
import dev.skyit.pao.client.Client;
import dev.skyit.pao.client.transfers.Transfer;
import dev.skyit.pao.exceptions.TransferException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Bank implements BankIFace, ServiceIFace {

    private List<Currency> currencyList = new ArrayList<>();
    private HashMap<Pair, Double> rates = new HashMap<>();
    private List<Client> clients = new ArrayList<>();

    private static Bank shared = null;
    private final Auditor auditor;


    public static Bank getInstance() {
        if (shared == null) {
            shared = new Bank();
            shared.loadClients();
        }
        return shared;
    }





    private Bank() {
        var currencies = BankDB.getInstance().getCurrenciesDao().readAll();
        currencyList.addAll(currencies);
        var lst =  BankDB.getInstance().getConvertersDao().readAll();
        lst.forEach((rate) -> {
            rates.put(new Pair(rate.getSourceId(), rate.getDestinationID()), rate.getRate());
        });

        auditor = new Auditor("audit/logs.txt");



    }

    private void loadClients(){
        var simpleClients = BankDB.getInstance().getSimpleClientsDao().readAll();
        var companyClients = BankDB.getInstance().getCompanyClientsDao().readAll();
        clients.addAll(companyClients);
        clients.addAll(simpleClients);
        clients.forEach(client -> {
            client.inject(this);
        });

        currencyList.forEach((currency -> clients.forEach((client -> client.registerAccount(currency.getId())))));


    }

    @Override
    public Currency getCurrencyById(Integer id) {
        auditor.logQuery("getCurrencyById()");
        for (Currency currency : currencyList) {
            if (currency.getId().equals(id)) return currency;
        }
        return null;
    }

    @Override
    public Double getExchangeRate(Integer sourceCurrency, Integer destinationCurrency) {
        auditor.logQuery("getExchangeRAte()");
        return rates.get(new Pair(sourceCurrency, destinationCurrency));
    }

    @Override
    public void addClient(Client client){
        auditor.logQuery("addClient()");
        clients.add(client);
    }


    @Override
    public Client getClientById(Integer id) {
        auditor.logQuery("getClientById()");
        for (Client client : clients) {
            if (client.getId().equals(id)) return client;
        }
        return null;
    }

    @Override
    public void registerAccountForClient(Integer clientId, Integer currencyId) {
        auditor.logQuery("registerAccountForClient()");
        getClientById(clientId).registerAccount(currencyId);
    }

    @Override
    public void makeTransferForClient(Integer clientId, Integer sourceId, Integer targetId, Double amount) throws TransferException {
        auditor.logQuery("makeTransferForClient()");
        getClientById(clientId).makeTransfer(sourceId, targetId, amount);
    }

    @Override
    public Transfer getLastTransfer(Integer clientId) {
        auditor.logQuery("getLastTransfer()");
        return getClientById(clientId).getLastTransfer();
    }

    @Override
    public Double getTotalBalanceInCurrency(Integer clientId, Integer currencyId) {
        auditor.logQuery("getTotalBalanceInCurrency()");
        return getClientById(clientId).getTotalBalanceInCurrency(currencyId);
    }

    @Override
    public List<Transfer> getTransfersBiggerThan(Integer clientId, Double amount) {
        auditor.logQuery("getTransfersBiggerThan()");
        return getClientById(clientId).getTransfersBiggerThan(amount);
    }

    @Override
    public List<Client> getAllClients() {
        auditor.logQuery("getAllClients()");
        return clients;
    }

    @Override
    public List<CurrencyModel> getCurrencyConvertTable() {
        auditor.logQuery("getCurrencyConvertTable()");
        return BankDB.getInstance().getConvertersDao().readAll().stream().map(c -> {
            return new CurrencyModel(getCurrencyById(c.getSourceId()), getCurrencyById(c.getDestinationID()), c.getRate());
        }).collect(Collectors.toList());
    }

    @Override
    public List<Transfer> getAllTransfers(Integer clientId) {
        auditor.logQuery("getAllTransfers()");
        return getClientById(clientId).getAllTransfers();
    }

    @Override
    public void removeClient(Integer clientId) {
        auditor.logQuery("removeClient()");
        Iterator<Client> it = clients.iterator();
        while (it.hasNext()) {
            Integer id = ((Client) it.next()).getId();
            if (id.equals(clientId)) it.remove();
        }
    }
}

package dev.skyit.pao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank implements BankIFace {

    private List<Currency> currencyList = new ArrayList<>();
    private HashMap<Pair, Double> rates = new HashMap<>();
    private List<Client> clients = new ArrayList<>();

    static final Bank shared = new Bank();
    private Bank() {
        currencyList.add(new Currency(0, "RON", "Romanian Leu"));
        currencyList.add(new Currency(1, "USD", "US Dollar"));
        currencyList.add(new Currency(2, "EUR", "Euro"));

        rates.put(new Pair(0,1), 0.22);
        rates.put(new Pair(1,0), 4.50);

        rates.put(new Pair(0,2), 0.21);
        rates.put(new Pair(2, 0), 4.85);

        rates.put(new Pair(1, 2), 0.93);
        rates.put(new Pair(2, 1), 1.08);
    }

    public void addDummyData(){
        addClient(new SimpleClient(0, "boss", shared));
        addClient(new SimpleClient(1, "secretary", shared));
        addClient(new SimpleClient(2, "manager", shared));

        addClient(new CompanyClient(4, "Sky-IT", shared));

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

    public void addClient(Client client){
        clients.add(client);
    }

    public Client getClientById(Integer id) {
        for (Client client : clients) {
            if (client.getId().equals(id)) return client;
        }
        return null;
    }

}

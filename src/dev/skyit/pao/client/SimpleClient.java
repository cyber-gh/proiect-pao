package dev.skyit.pao.client;

import dev.skyit.pao.api.BankIFace;
import dev.skyit.pao.client.transfers.Transfer;
import dev.skyit.pao.exceptions.MissingExchangeRateException;
import dev.skyit.pao.exceptions.TransferException;

public class SimpleClient extends Client {



    public SimpleClient(Integer id, String alias) {
        super(id, alias);
    }

    @Override
    public void makeTransfer(Integer sourceId, Integer destinationId, Double amount) throws TransferException {
        Double rate = bank.getExchangeRate(sourceId, destinationId);
        if (rate == null) throw new MissingExchangeRateException();
        Double valueInInitialCurrency = amount;
        Double valueInDestinationCurrency = amount * rate;
        modifyAccount(sourceId, -valueInInitialCurrency);
        modifyAccount(destinationId, valueInDestinationCurrency);
        String sourceCurrencyCode = bank.getCurrencyById(sourceId).getCode();
        String destinationCurrencyCode = bank.getCurrencyById(destinationId).getCode();
        Transfer transfer = new Transfer(id, lastTransactionId++, sourceId, destinationId, sourceCurrencyCode, destinationCurrencyCode, rate, valueInInitialCurrency, valueInDestinationCurrency);
        addTransfer(transfer);
    }
}

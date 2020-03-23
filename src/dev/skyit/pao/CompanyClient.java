package dev.skyit.pao;

import dev.skyit.pao.exceptions.MissingExchangeRateException;
import dev.skyit.pao.exceptions.TransferException;

public class CompanyClient extends Client {

    private final Double commission = 0.05;

    public CompanyClient(Integer id, String alias, BankIFace bank) {
        super(id, alias, bank);
    }

    @Override
    public void makeTransfer(Integer sourceId, Integer destinationId, Double amount) throws TransferException {
        Double rate = bank.getExchangeRate(sourceId, destinationId);
        if (rate == null) throw new MissingExchangeRateException();
        Double valueInInitialCurrency = amount;
        Double valueInDestinationCurrency = amount * rate;
        Double comm = valueInDestinationCurrency * commission;
        Double finalValue = valueInDestinationCurrency - comm;
        modifyAccount(sourceId, -valueInInitialCurrency);

        modifyAccount(destinationId, finalValue);
        String sourceCurrencyCode = bank.getCurrencyById(sourceId).getCode();
        String destinationCurrencyCode = bank.getCurrencyById(destinationId).getCode();
        Transfer transfer = new CompanyTransfer(lastTransactionId++, sourceId, destinationId, sourceCurrencyCode, destinationCurrencyCode, rate, valueInInitialCurrency, valueInDestinationCurrency, comm);
        addTransfer(transfer);
    }
}

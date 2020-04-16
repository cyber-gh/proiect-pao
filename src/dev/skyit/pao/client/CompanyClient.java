package dev.skyit.pao.client;

import dev.skyit.pao.api.BankIFace;
import dev.skyit.pao.client.transfers.CompanyTransfer;
import dev.skyit.pao.client.transfers.Transfer;
import dev.skyit.pao.exceptions.MissingExchangeRateException;
import dev.skyit.pao.exceptions.TransferException;

public class CompanyClient extends Client {

    private Double commission = 0.05;

    @Override
    public String toString() {
        return "CompanyClient{" +
                "commission=" + commission +
                ", id=" + id +
                ", alias='" + alias + '\'' +
                ", transfers=" + transfers +
                ", accounts=" + accounts +
                ", bank=" + bank +
                '}';
    }

    public CompanyClient(Integer id, String alias,Double commission , BankIFace bank) {
        super(id, alias, bank);
        this.commission = commission;
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
        Transfer transfer = new CompanyTransfer(id, lastTransactionId++, sourceId, destinationId, sourceCurrencyCode, destinationCurrencyCode, rate, valueInInitialCurrency, valueInDestinationCurrency, comm);
        addTransfer(transfer);
    }
}

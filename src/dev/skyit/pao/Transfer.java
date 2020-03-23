package dev.skyit.pao;

import java.util.Objects;

public class Transfer {
    protected final Integer transactionId;
    protected final Integer sourceCurrencyId;
    protected final Integer destinationCurrencyId;
    protected final String sourceCurrencyCode;
    protected final String destinationCurrencyCode;
    protected final Double exchangeRate;
    protected final Double amountInSourceCurrency;
    protected final Double amountInDestinationCurrency;

    public Transfer(Integer transactionId, Integer sourceCurrencyId, Integer destinationCurrencyId, String sourceCurrencyCode, String destinationCurrencyCode, Double exchangeRate, Double amount, Double amountInDestinationCurrency) {
        this.transactionId = transactionId;
        this.sourceCurrencyId = sourceCurrencyId;
        this.destinationCurrencyId = destinationCurrencyId;
        this.sourceCurrencyCode = sourceCurrencyCode;
        this.destinationCurrencyCode = destinationCurrencyCode;
        this.exchangeRate = exchangeRate;
        this.amountInSourceCurrency = amount;
        this.amountInDestinationCurrency = amountInDestinationCurrency;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public Integer getSourceCurrencyId() {
        return sourceCurrencyId;
    }

    public Integer getDestinationCurrencyId() {
        return destinationCurrencyId;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public Double getAmountInSourceCurrency() {
        return amountInSourceCurrency;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transactionId=" + transactionId +
                ", sourceCurrencyCode='" + sourceCurrencyCode + '\'' +
                ", destinationCurrencyCode='" + destinationCurrencyCode + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", amountInSourceCurrency=" + amountInSourceCurrency +
                ", amountInDestinationCurrency=" + amountInDestinationCurrency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transfer)) return false;
        Transfer transfer = (Transfer) o;
        return getTransactionId().equals(transfer.getTransactionId()) &&
                getSourceCurrencyId().equals(transfer.getSourceCurrencyId()) &&
                getDestinationCurrencyId().equals(transfer.getDestinationCurrencyId()) &&
                getExchangeRate().equals(transfer.getExchangeRate()) &&
                getAmountInSourceCurrency().equals(transfer.getAmountInSourceCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionId(), getSourceCurrencyId(), getDestinationCurrencyId(), getExchangeRate(), getAmountInSourceCurrency());
    }

    public String getSourceCurrencyCode() {
        return sourceCurrencyCode;
    }

    public String getDestinationCurrencyCode() {
        return destinationCurrencyCode;
    }
}

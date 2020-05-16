package dev.skyit.pao.utility;


public class CurrencyModel {
    private final Currency sourceCurrency;
    private final Currency destinationCurrency;
    private final Double rate;

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public Currency getDestinationCurrency() {
        return destinationCurrency;
    }

    public Double getRate() {
        return rate;
    }

    public CurrencyModel(Currency sourceCurrency, Currency destinationCurrency, Double rate) {
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.rate = rate;
    }
}
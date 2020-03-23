package dev.skyit.pao;

public interface BankIFace {
    public Currency getCurrencyById(Integer id);
    public Double getExchangeRate(Integer sourceCurrency, Integer destinationCurrency);
}

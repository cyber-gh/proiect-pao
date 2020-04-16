package dev.skyit.pao.api;

import dev.skyit.pao.utility.Currency;

public interface BankIFace {
    public Currency getCurrencyById(Integer id);
    public Double getExchangeRate(Integer sourceCurrency, Integer destinationCurrency);
}

package dev.skyit.pao;

import java.util.Objects;

public class BalanceAccount {
    private final Integer currencyId;
    private Double amount;

    @Override
    public String toString() {
        return "BalanceAccount{" +
                "currencyId=" + currencyId +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BalanceAccount)) return false;
        BalanceAccount that = (BalanceAccount) o;
        return Objects.equals(getCurrencyId(), that.getCurrencyId()) &&
                Objects.equals(getAmount(), that.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrencyId(), getAmount());
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void modifyAmount(Double amount) {
        this.amount += amount;
    }

    public BalanceAccount(Integer currencyId, Double amount) {
        this.currencyId = currencyId;
        this.amount = amount;
    }
}

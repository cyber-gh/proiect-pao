package dev.skyit.pao.client.transfers;

public class CompanyTransfer extends Transfer {
    public final Double appliedCommision;

    public CompanyTransfer(Integer clientId, Integer transactionId, Integer sourceCurrencyId, Integer destinationCurrencyId, String sourceCurrencyCode, String destinationCurrencyCode, Double exchangeRate, Double amount, Double amountInDestinationCurrency, Double appliedCommision) {
        super(clientId, transactionId, sourceCurrencyId, destinationCurrencyId, sourceCurrencyCode, destinationCurrencyCode, exchangeRate, amount, amountInDestinationCurrency);
        this.appliedCommision = appliedCommision;
    }

    @Override
    public String toString() {
        return "CompanyTransfer{" +
                "appliedCommision=" + appliedCommision +
                ", transactionId=" + transactionId +
                ", sourceCurrencyCode='" + sourceCurrencyCode + '\'' +
                ", destinationCurrencyCode='" + destinationCurrencyCode + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", amountInSourceCurrency=" + amountInSourceCurrency +
                ", amountInDestinationCurrency=" + amountInDestinationCurrency +
                '}';
    }
}

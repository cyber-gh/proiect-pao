package dev.skyit.pao.exceptions;

public class MissingExchangeRateException extends TransferException {
    @Override
    public String getLocalizedMessage() {
        return "Conversion unavailable for these currencies";
    }
}

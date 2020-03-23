package dev.skyit.pao.exceptions;

public class UnknownCurrencyException extends TransferException {
    @Override
    public String getLocalizedMessage() {
        return "Can't find the desired currency";
    }
}

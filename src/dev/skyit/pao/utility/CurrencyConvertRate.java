package dev.skyit.pao.utility;

public class CurrencyConvertRate {
    private final Integer sourceId;
    private final Integer destinationID;
    private final Double rate;

    public CurrencyConvertRate(Integer sourceId, Integer destinationID, Double rate) {
        this.sourceId = sourceId;
        this.destinationID = destinationID;
        this.rate = rate;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public Integer getDestinationID() {
        return destinationID;
    }

    public Double getRate() {
        return rate;
    }
}

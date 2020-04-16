package dev.skyit.pao.utility;

public class ConvercyRate {
    private final Integer sourceId;
    private final Integer destinationID;
    private final Double rate;

    public ConvercyRate(Integer sourceId, Integer destinationID, Double rate) {
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

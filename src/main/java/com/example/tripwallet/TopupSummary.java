package com.example.tripwallet;

public class TopupSummary {
    private String cardType;
    private String lastFour;
    private double authorized;
    private String transType;
    private long pointsAuthorized;

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransType() {
        return transType;
    }

    public TopupSummary(String cardType, String lastFour, double authorized, String transType, long pointsAuthorized) {
        this.cardType = cardType;
        this.lastFour = lastFour;
        this.authorized = authorized;
        this.transType = transType;
        this.pointsAuthorized = pointsAuthorized;
    }

    public long getPointsAuthorized() {
        return pointsAuthorized;
    }

    public void setPointsAuthorized(long pointsAuthorized) {
        this.pointsAuthorized = pointsAuthorized;
    }

    public String getCardType() {
        return cardType;
    }

    public String getLastFour() {
        return lastFour;
    }

    public double getAuthorized() {
        return authorized;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setLastFour(String lastFour) {
        this.lastFour = lastFour;
    }

    public void setAuthorized(double authorized) {
        this.authorized = authorized;
    }
}

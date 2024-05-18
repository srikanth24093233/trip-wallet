package com.example.tripwallet;

public class TopupSummary {
    private String cardType;
    private String lastFour;
    private double authorized;
    private String transType;
    private long pointsAuthorized;
    private boolean pointsTrans;
    private boolean cashTrans;
    private long pointsBalance;

    public void setPointsBalance(long pointsBalance) {
        this.pointsBalance = pointsBalance;
    }

    public long getPointsBalance() {
        return pointsBalance;
    }

    public boolean isCashTrans() {
        return cashTrans;
    }

    public void setCashTrans(boolean cashTrans) {
        this.cashTrans = cashTrans;
    }

    public boolean isPointsTrans() {
        return pointsTrans;
    }

    public void setPointsTrans(boolean pointsTrans) {
        this.pointsTrans = pointsTrans;
    }

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

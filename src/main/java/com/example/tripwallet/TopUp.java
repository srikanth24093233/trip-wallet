package com.example.tripwallet;

public class TopUp {

    public long tripId;
    public String date;
    public String transType;
    public String lastFour;
    public String cardType;
    public double topUpAmount;
    public long pointsAmount;
    public String service;
    public String item;
    public boolean pointsTras;
    public boolean cashTrans;
    public long pointsEarned;

    public void setPointsEarned(long pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public long getPointsEarned() {
        if(transType != null && transType.equals("CASH") && service != null && !service.equals("WALLET")) {
            double points = topUpAmount/10;
            pointsEarned = (long)points;
        }else{
            pointsEarned = 0 ;
        }

        return pointsEarned;
    }

    public void setCashTrans(boolean cashTrans) {
        this.cashTrans = cashTrans;
    }

    public boolean isCashTrans() {
        return cashTrans;
    }

    public void setPointsTras(boolean pointsTras) {
        this.pointsTras = pointsTras;
    }

    public boolean isPointsTras() {
        return pointsTras;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public long getTripId() {
        return tripId;
    }

    public TopUp(long tripId, String date,String transType,String lastFour, String cardType, double topUpAmount, long pointsAmount,  String service, String item){
        this.lastFour=lastFour;
        this.cardType=cardType;
        this.topUpAmount=topUpAmount;
        this.date=date;
        this.transType=transType;
        this.pointsAmount=pointsAmount;
        this.tripId=tripId;
        this.service=service;
        this.item=item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTransType() {
        return transType;
    }

    public long getPointsAmount() {
        return pointsAmount;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public void setPointsAmount(long pointsAmount) {
        this.pointsAmount = pointsAmount;
    }


    public void setService(String service) {
        this.service = service;
    }

    public void setLastFour(String lastFour) {
        this.lastFour = lastFour;
    }

    public String getService() {
        return service;
    }

    public String getLastFour() {
        return lastFour;
    }

    public String getCardType() {
        return cardType;
    }

    public double getTopUpAmount() {
        return topUpAmount;
    }

    public String getDate() {
        return date;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setTopUpAmount(double topUpAmount) {
        this.topUpAmount = topUpAmount;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

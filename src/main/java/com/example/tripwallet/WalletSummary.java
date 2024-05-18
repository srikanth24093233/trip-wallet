package com.example.tripwallet;

import java.util.ArrayList;
import java.util.List;

public class WalletSummary {

    public double cashUsed = 0.0;
    public double cashBalance = 0.0;
    public long pointsUsed = 0;
    public long pointsBalance = 0;
    public double totalCashAuthorized = 0.0;
    public long totalPointsAuthorized = 0;
    public double totalAuthorizedAmount = 0.0;
    public boolean hasNoPoints;

    public void setHasNoPoints(boolean hasPoints) {
        this.hasNoPoints = hasPoints;
    }

    public boolean isHasNoPoints() {
        return hasNoPoints;
    }

    List<TopupSummary> topupSummaryList = new ArrayList<>();
    List<TopUp> transactions = new ArrayList<>();

    public void setTransactions(List<TopUp> transactions) {
        this.transactions = transactions;
    }

    public List<TopUp> getTransactions() {
        return transactions;
    }

    public void setTopupSummaryList(List<TopupSummary> topupSummaryList) {
        this.topupSummaryList = topupSummaryList;
    }

    public List<TopupSummary> getTopupSummaryList() {
        return topupSummaryList;
    }

    public double getCashUsed() {
        return cashUsed;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public long getPointsUsed() {
        return pointsUsed;
    }

    public long getPointsBalance() {
        return pointsBalance;
    }

    public double getTotalCashAuthorized() {
        return cashUsed+cashBalance;
    }

    public long getTotalPointsAuthorized() {
        return pointsUsed+pointsBalance;
    }

    public double getTotalAuthorizedAmount() {
        return totalCashAuthorized+totalPointsAuthorized;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void setPointsUsed(long pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public void setPointsBalance(long pointsBalance) {
        this.pointsBalance = pointsBalance;
    }

    public void setTotalCashAuthorized(double totalCashAuthorized) {
        this.totalCashAuthorized = totalCashAuthorized;
    }

    public void setTotalPointsAuthorized(long totalPointsAuthorized) {
        this.totalPointsAuthorized = totalPointsAuthorized;
    }

    public void setTotalAuthorizedAmount(double totalAuthorizedAmount) {
        this.totalAuthorizedAmount = totalAuthorizedAmount;
    }

    public void setCashUsed(double cashUsed) {
        this.cashUsed = cashUsed;
    }
}

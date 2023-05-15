package com.example.esport;

public class UsersDetail {

    private String transactionName;
    private String transactionTime;
    private String transactionLocation;
    private String transactionPrice;

    public UsersDetail(String transactionName, String transactionTime, String transactionLocation, String transactionPrice) {
        this.transactionName = transactionName;
        this.transactionTime = transactionTime;
        this.transactionLocation = transactionLocation;
        this.transactionPrice = transactionPrice;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionLocation() {
        return transactionLocation;
    }

    public void setTransactionLocation(String transactionLocation) {
        this.transactionLocation = transactionLocation;
    }

    public String getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(String transactionPrice) {
        this.transactionPrice = transactionPrice;
    }
}

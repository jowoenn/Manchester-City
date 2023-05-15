package com.example.esport;

public class TicketsDetail {

    private String ticketName;
    private String ticketTime;
    private String ticketLocation;
    private String ticketPrice;

    public TicketsDetail(String ticketName, String ticketTime, String ticketLocation, String ticketPrice) {
        this.ticketName = ticketName;
        this.ticketTime = ticketTime;
        this.ticketLocation = ticketLocation;
        this.ticketPrice = ticketPrice;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(String ticketTime) {
        this.ticketTime = ticketTime;
    }

    public String getTicketLocation() {
        return ticketLocation;
    }

    public void setTicketLocation(String ticketLocation) {
        this.ticketLocation = ticketLocation;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

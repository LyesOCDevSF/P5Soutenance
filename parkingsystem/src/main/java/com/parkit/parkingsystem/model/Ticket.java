package com.parkit.parkingsystem.model;


import java.util.Date;

public class Ticket {
    private int id;
    private ParkingSpot parkingSpot;
    private String vehicleRegNumber;
    private double price;
    private Date inTime;
    private Date outTime;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getInTime() {
        //this.inTime = new Date(inTime.getTime());
        return this.inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = new Date(inTime.getTime()) ; //inTime
    }

    public Date getOutTime() {
        //this.outTime = new Date(outTime.getTime());
        return this.outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = new Date(outTime.getTime());
    }

    /*public void saveTicket(Ticket ticket) {
    }*/
}

package com.parkit.parkingsystem.service;

import java.util.concurrent.TimeUnit;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    private int visit;

    /**
     * @param ticket
     */
    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        

        int inHour = ticket.getInTime().getHours(); // getInTime = new Date()
       int outHour = ticket.getOutTime().getHours();
        
       Long inHourInMillis = ticket.getInTime().getTime();
       Long outHourInMillis = ticket.getOutTime().getTime();
       
       double durationMin = (double) TimeUnit.MILLISECONDS.toMinutes(outHourInMillis - inHourInMillis);
      /* double durationHour = Precision.round((durationMin / 60), 2);*/
       double durationHour = durationMin / 60;

        //TODO: Some tests are failing here. Need to check if this logic is correct
        int duration = outHour - inHour;

        if (duration > 30){
            TicketDAO ticketDAO = new TicketDAO();
            
        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
                break;
            }
            case BIKE: {
                ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
        if(visit > 1 ) {
            double discountedPrice = ticket.getPrice() * Fare.REDUCTION;
            ticket.setPrice(discountedPrice);	
        }
        }
        else{
            ticket.setPrice(0);
        }
    }
    }

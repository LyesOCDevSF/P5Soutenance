package com.parkit.parkingsystem.service;




import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.dao.TicketDAO;
import java.util.concurrent.TimeUnit;

/*import org.apache.commons.math3.util.Precision;*/


public class FareCalculatorService {  

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }
        Long inHourInMillis = ticket.getInTime().getTime();
        Long outHourInMillis = ticket.getOutTime().getTime();
        double durationMin = (double) TimeUnit.MILLISECONDS.toMinutes(outHourInMillis - inHourInMillis);
        double durationHour = durationMin / 60;
		TicketDAO ticketDAO = new TicketDAO();
		int visit = ticketDAO.getRecurrence(ticket.getVehicleRegNumber());
		if(visit > 1){ ticket.setRecurrent(true);}

        if (durationMin > 30) {
        	switch (ticket.getParkingSpot().getParkingType()){
	            case CAR: {
	                ticket.setPrice(durationHour * Fare.CAR_RATE_PER_HOUR);
	                break;
	            }
	            case BIKE: {
	                ticket.setPrice(durationHour * Fare.BIKE_RATE_PER_HOUR);
	                break;
	            }
	            default: throw new IllegalArgumentException("Unkown Parking Type");
        	}
        	if( ticket.getRecurrent()== true) {
        		double discountedPrice = ticket.getPrice() * Fare.REDUCTION;
        	    ticket.setPrice(discountedPrice);
				System.out.println("WelcomeBack");
        	}
        }
        else {
        	ticket.setPrice(0);
			}
        }            
    }

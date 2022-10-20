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
  
       // int inHour = ticket.getInTime().getHours(); // getInTime = new Date()
       // int outHour = ticket.getOutTime().getHours();
        
        Long inHourInMillis = ticket.getInTime().getTime();
        Long outHourInMillis = ticket.getOutTime().getTime();
        
        double durationMin = (double) TimeUnit.MILLISECONDS.toMinutes(outHourInMillis - inHourInMillis);
       /* double durationHour = Precision.round((durationMin / 60), 2);*/
        double durationHour = durationMin / 60;
 
       

        //TODO: Some tests are failing here. Need to check if this logic is correct
        //int duration = outHour - inHour;
         
        if (durationMin > 30) {
        	TicketDAO ticketDAO = new TicketDAO();
            int visit = ticketDAO.getRecurrence(ticket.getVehicleRegNumber());
            ticket.getParkingSpot().setParkingType(null);
        	switch (ticket.getParkingSpot().getParkingType()){ // trouver comment appliquer la réduction de 30 min si le vehicule reste moins de 30 minutes 
	            case CAR: {
	                ticket.setPrice(durationHour * Fare.CAR_RATE_PER_HOUR); // double durationHour pose probleme ?
	                break;
	            }
	            case BIKE: {
	                ticket.setPrice(durationHour * Fare.BIKE_RATE_PER_HOUR);
	                break;
	            }
	            default: throw new IllegalArgumentException("Unkown Parking Type");
        	}
        	if(visit > 1 ) {
        		double discountedPrice = ticket.getPrice() * Fare.REDUCTION;
        	    ticket.setPrice(discountedPrice);	
        	}
        }
        else {
        	ticket.setPrice(0);
        }            
    }
}
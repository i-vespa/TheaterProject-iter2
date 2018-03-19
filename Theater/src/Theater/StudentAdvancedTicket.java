package Theater;

import java.io.Serializable;
/*
 * Student advanced ticket is a descendant of Ticket class hierarchy. It contains within it
 * a serial number, showdate, and ticket price. The ticket price is 75% of 
 * the advanced ticket price, and therefore 37.5% of the regular ticket price.
 * */
import java.util.Calendar;

public class StudentAdvancedTicket extends Ticket implements Serializable{

	private static final long serialVersionUID = 1L;

	/*
	 * Student advanced ticket constructor calls the superclass Tickets constructor to set the showdate
	 * to the specified value. The Ticket constructor also instantiates the serial number of
	 * the StudentAdvancedTicket. Instantiation of the ticket price is handled by the Student subclass.
	 * */
	public StudentAdvancedTicket(Calendar showDate, double ticketPrice) {
		super(showDate);
		this.ticketPrice = (ticketPrice*.70)*.50;
	}
	
	
	
}
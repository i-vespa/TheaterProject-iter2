package Theater;

import java.io.Serializable;
/*
 * Advanced ticket is a descendant of Ticket class hierarchy. It contains within it
 * a serial number, showdate, and ticket price. The ticket price is 50% of 
 * the regular ticket price.
 * */
import java.util.Calendar;

public class AdvancedTicket extends Ticket implements Serializable{

	private static final long serialVersionUID = 1L;

	/*
	 * Advanced ticket constructor calls the superclass Tickets constructor to set the showdate
	 * to the specified value. The Ticket constructor also instantiates the serial number of
	 * the AdvancedTicket. Instantiation of the ticket price is handled by the Advanced subclass.
	 * */
	public AdvancedTicket(Calendar showDate, double ticketPrice) {
		super(showDate);
		this.ticketPrice = ticketPrice*.50;
	}
	
	
	
}
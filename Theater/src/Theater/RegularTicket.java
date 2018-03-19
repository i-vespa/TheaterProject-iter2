package Theater;

import java.io.Serializable;
/*
 * Regular ticket is a descendant of Ticket class hierarchy. It contains within it
 * a serial number, showdate, and ticket price. The ticket price equals that
 * specified by the show ticket price.
 * */
import java.util.Calendar;

public class RegularTicket extends Ticket implements Serializable{

	private static final long serialVersionUID = 1L;

	/*
	 * Regular ticket constructor calls the superclass Tickets constructor to set the showdate
	 * to the specified value. The Ticket constructor also instantiates the serial number of
	 * the Ticket. Instantiation of the ticket price is handled by the RegularTicket subclass.
	 * */
	public RegularTicket(Calendar showDate, double ticketPrice) {
		super(showDate);
		this.ticketPrice = ticketPrice;
	}
	
	
	
}

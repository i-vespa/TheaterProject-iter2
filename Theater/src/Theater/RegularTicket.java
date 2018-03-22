package Theater;

import java.io.Serializable;
import java.util.Calendar;

/**
 * RegularTicket represents a ticket which is created for a show on the night the
 * show is taking place. It modifies the ticket price accordingly.
 * @author Vanessa Esaw
 */
public class RegularTicket extends Ticket implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Regular ticket constructor calls the superclass Tickets constructor to set the showdate
	 * to the specified value. The Ticket constructor also instantiates the serial number of
	 * the Ticket. Instantiation of the ticket price is handled by the RegularTicket subclass.
	 * @param showDate the date the show takes place
	 * @param ticketPrice the price of the ticket
	 */
	public RegularTicket(Calendar showDate, double ticketPrice) {
		super(showDate, ticketPrice);
	}
	
	@Override
    public String toString() {
        return "Regular " + super.toString();
    } 
}

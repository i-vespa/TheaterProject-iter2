package Theater;

import java.io.Serializable;
import java.util.Calendar;

/**
 * AdvancedTicket represents a ticket which is created in advanced to a showing
 * the customer is going to attend. It modifies the ticket price accordingly to
 * provide the customer a discount for this. (50% discount for advanced)
 * @author Vanessa Esaw
 */
public class AdvancedTicket extends Ticket implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Advanced ticket constructor calls the superclass Tickets constructor to set the showdate
	 * to the specified value. The Ticket constructor also instantiates the serial number of
	 * the AdvancedTicket. Instantiation of the ticket price is handled by the Advanced subclass.
	 * @param showDate the date the show takes place
	 * @param ticketPrice the price of the ticket
	 */
	public AdvancedTicket(Calendar showDate, double ticketPrice) {
		super(showDate, (ticketPrice*.70));
	}
	
    @Override
    public String toString() {
        return "Advance " + super.toString();
    } 
}

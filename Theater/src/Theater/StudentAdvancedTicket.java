package Theater;

import java.io.Serializable;
import java.util.Calendar;

/**
 * StudentAdvancedTicket represents a ticket which is created in advance for a show
 * the student is going to attend. It modifies the ticket price accordingly and provides
 * a discount to the student. (70% discount for being a student ticket, and then another
 * 50% discount off that price for being an advanced ticket)
 * @author Vanessa Esaw
 *
 */
public class StudentAdvancedTicket extends Ticket implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Student advanced ticket constructor calls the superclass Tickets constructor to set the show date
	 * to the specified value. The Ticket constructor also instantiates the serial number of
	 * the StudentAdvancedTicket. Instantiation of the ticket price is handled by the Student subclass.
	 * @param showDate the date the show takes place
	 * @param ticketPrice the price of the ticket
	 */
	public StudentAdvancedTicket(Calendar showDate, double ticketPrice) {
		super(showDate, ((ticketPrice*.70)*.50));
	}
}

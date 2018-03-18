
package Theater;
import java.io.Serializable;
import java.util.Calendar;

public abstract class Ticket implements Serializable{

	private static final long serialVersionUID = 1L;
	private String serialNumber;
	private Calendar showDate;
	double ticketPrice;
	private static final String TICKET_STRING = "Tkt";
	/*
	 * Constructor for the Ticket superclass. Instantiates the shared attributes
	 * serialNumber, ticketPrice, and the serialID of the tickets. 
	 * 
	 * Property below may change in near future -- where instantiate ticketPrice
	 * Note that the Ticket prices will be updated appropriately in the class hierarchy's
	 * subclass constructors based on the instantiated ticket price provided to the superclass
	 * 
	 * */
	public Ticket(Calendar showDate, double ticketPrice) {
		//super();
		//this.ticketPrice = ticketPrice;
		
		this.serialNumber = serialNumber;
		this.showDate = showDate;
		serialNumber = TICKET_STRING + (TicketSerialNumberServer.instance().getId());
		//+ (CustomerIdServer.instance()).getId();
	}
	
	@Override
	public String toString() {
		return "Ticket [serialNumber=" + serialNumber + ", showDate=" + showDate + 
				", ticketPrice=" + ticketPrice + "]";
	}

	

}

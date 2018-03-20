
package Theater;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Ticket class is the abstract superclass in the Ticket class hierarchy. It contains
 * the basic information shared between all ticket types. 
 * Note that it implements the Matchable interface. This is in order for descendant ticket
 * types to be added to a generic list, with matching capability. 
 * 
 */

public abstract class Ticket implements Matchable<String>,Serializable{

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
	public Ticket(Calendar showDate) {//, double ticketPrice) {
		//super();
		//this.ticketPrice = ticketPrice;
		
		//this.serialNumber = serialNumber;
		this.showDate = showDate;
		serialNumber = TICKET_STRING + (TicketSerialNumberServer.instance().getId());
		//+ (CustomerIdServer.instance()).getId();
	}
	
	@Override
	//need to format the date otherwise will print out ALL attributes of date object
	//print of form MM/dd/yyyy
	public String toString() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String formatted = sdf.format(showDate.getTime());
		
		return "\nTicket [serialNumber=" + serialNumber + ", showDate=" + formatted + //showDate + 
				", ticketPrice=" + ticketPrice + "]";
	}

	/*Common functions possibly to be used between subclasses in hierarchy*/
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Calendar getShowDate() {
		return showDate;
	}

	public void setShowDate(Calendar showDate) {
		this.showDate = showDate;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	/**
	 * Checks if a ticket is the same as another with the given
	 * serial number
	 * @param serialNumber is the serialNumber of another ticket
	 * @return true if the tickets are the same, otherwise false
	 */
    @Override
	public boolean matches(String serialNumber) {
    	return this.serialNumber.equals(serialNumber) ? true : false;
    	
	}
    
}

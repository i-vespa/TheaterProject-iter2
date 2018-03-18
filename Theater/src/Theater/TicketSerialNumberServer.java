package Theater;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * TicketSerialNumberServer class serves as a way of getting a new, unique serial numbers
 * for tickets created based on the abstract class Ticket. It is then used within the descendants 
 * of the the Ticket class hierarchy: regularTickets, advandedTickets, and studentAdvancedTickets
 * Will be saved along with the Theater class when the program is shutdown.
 * @author Vanessa Esaw
 */
public class TicketSerialNumberServer implements Serializable {

	private static final long serialVersionUID = 1L;
	private  int idCounter;
	private static TicketSerialNumberServer server;
	
	// Private constructor for singleton pattern
	private TicketSerialNumberServer() {
		idCounter = 1;
	}
	
	/**
	 * Supports the singleton pattern
	 * @return the singleton object
	 */
	public static TicketSerialNumberServer instance() {
		if (server == null) {
			return (server = new TicketSerialNumberServer());
		} else {
			return server;
		}
	}
	
	/**
	 * Gets customer id
	 * @return id of the Customer
	 */
	public int getId() {
		return idCounter++;
	}
	
	/**
	 * String form of the collection
	 */
	@Override
	public String toString() {
		return ("SerialNumberServer" + idCounter);
	}
	
	/**
	* Retrieves the server object
	* @param input input stream for deserialization.
	*/
	public static void retrieve(ObjectInputStream input) {
	    try {
	        server = (TicketSerialNumberServer) input.readObject();
	    } catch(IOException ioe) {
	        ioe.printStackTrace();
	    } catch(Exception cnfe) {
	        cnfe.printStackTrace();
	    }
	}
}


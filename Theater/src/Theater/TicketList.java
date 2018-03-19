package Theater;

import java.io.Serializable;
import java.util.Iterator;
/*
 * This ticket list can be made up of Regular, Advanced, and Student Advanced tickets. 
 * Tickets are stored within a ticketList within each customer as they buy tickets from shows.
 * */
public class TicketList extends GenericList<Ticket, String> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TicketList() {
    }

	/**
	 * Search for a specific Ticket in list based on the Serial Number.
	 * @param serialNumber of Ticket to query
	 * @return Ticket found in the list, otherwise, null if no Ticket was found
	 */
	public Ticket search(String serialNumber){
		return super.search(serialNumber);
	}
    
	/**
	* Removes Ticket from list, based on serial number number given.
	* Returns boolean true if Ticket found and removed, otherwise it returns false
	* @param serialNumber the serial number of the Ticket to remove
	* @return true if Ticket was found and removed, otherwise false.
	*/
	public boolean removeTicket(String serialNumber){
		return super.remove(serialNumber);
	}
    
	/**
	* Inserts Ticket in to the Ticket list
	* @param ticket the Ticket to insert
	* @return true if inserted successfully, else false
	*/
	public boolean insertTicket(Ticket ticket){
		return super.insert(ticket);
	}
    
	/**
	* Gets an iterator for the Ticket list
	* @return Iterator of the Ticket list
	*/
	public Iterator<Ticket> getTicketList(){
		return super.iterator();
	}

	@Override
	public String toString() {
		return super.toString();
	}

}

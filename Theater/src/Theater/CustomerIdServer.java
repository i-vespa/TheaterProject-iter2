package Theater;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * ClientIDServer class serves as a way of getting a new, unique ID for customer creation.
 * Will be saved along with the Theater class when the program is shutdown.
 * @author Vanessa Esaw
 */
public class CustomerIdServer implements Serializable {

	private static final long serialVersionUID = 1L;
	private  int idCounter;
	private static CustomerIdServer server;
	
	// Private constructor for singleton pattern
	private CustomerIdServer() {
		idCounter = 1;
	}
	
	/**
	 * Supports the singleton pattern
	 * @return the singleton object
	 */
	public static CustomerIdServer instance() {
		if (server == null) {
			return (server = new CustomerIdServer());
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
		return ("IdServer" + idCounter);
	}
	
	/**
	* Retrieves the server object
	* @param input input stream for deserialization.
	*/
	public static void retrieve(ObjectInputStream input) {
	    try {
	        server = (CustomerIdServer) input.readObject();
	    } catch(IOException ioe) {
	        ioe.printStackTrace();
	    } catch(Exception cnfe) {
	        cnfe.printStackTrace();
	    }
	}
}

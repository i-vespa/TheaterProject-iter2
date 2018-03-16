package Theater;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * ClientList represents a singleton class which encloses a list of clients.
 * ClientList is saved with Theater class on program shutdown.
 * @author David Jaqua
 */
public class ClientList implements Serializable{

	private static final long serialVersionUID = 1L;
	/*
	 * Singleton instance of the ClientList
	 */
	private static ClientList clientListInstance;
	private List clientList = new LinkedList<>();

	
	// Creates singleton instance of the ClientList
	private ClientList() {
	}
	
	/**
	 * Retrieves the singleton instance of ClientList
	 * @return ClientList instance
	 */
	public static ClientList instance(){
		if (clientListInstance == null){
			return (clientListInstance = new ClientList());
		} else{
			return clientListInstance;
		}
	}
	
	/**
	 * Searches the clientList for a client with the specified client ID
	 * @param clientID ID of the client to search for
	 * @return Client of with the given ID, otherwise null
	 */
	public Client search(String clientID){
		for(Iterator iterator = clientList.iterator(); iterator.hasNext();) {
			Client client = (Client) iterator.next();
			if (client.getClientID().equals(clientID)) {
				return client; // client found with id
			}
		}
		return null; // no client found with id
	}
	
	/**
	 * Inserts the client into the list
	 * @param client an object of the Client class.
	 * @return true if client added to clientList, false otherwise.
	 */
	public boolean insertClient(Client client){
		return clientList.add(client);
	}
	
	/**
	 * Removes the client with the specified client ID from the list
	 * @param clientID ID of the client to remove
	 * @return true if the client with the given id was removed, otherwise, false
	 */
	public boolean removeClient(String clientID){
		for(Iterator iterator = clientList.iterator(); iterator.hasNext();) {
			Client client = (Client) iterator.next();
			if (client.getClientID().equals(clientID)) {
				// we found the client with the specified client ID, remove them from list
				return clientList.remove(client);
			}
		}
		return false;
	}
	
	/**
	 * Gets an iterator of the client list
	 * @return client list iterator
	 */
	public Iterator<Client> getClients(){
		return clientList.iterator();
	}
	
	@Override
	public String toString() {
		return clientList.toString();
	}
}

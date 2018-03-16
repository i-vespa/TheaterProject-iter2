/**
 * Project: OOAD_Project_1_Theater
 */

package Theater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;



/** 
 * Theater class 
 *
 * @author Vanessa Esaw, David Jaqua, Franklin Ortega
 * Date: Feb 16, 2018
 */
class Theater implements Serializable {
    public static final long serialVersionUID = 1L;
    private ClientList clientList;
    private CustomerList customerList;
	private ShowList showList;
    public static final int CLIENT_NOT_FOUND = 1;
    public static final int CLIENT_HAS_UPCOMING_SHOW = 2;
    public static final int CUSTOMER_NOT_FOUND = 3;
    public static final int CUSTOMER_HAS_ONE_CARD_ONLY = 4;
    public static final int ACTION_COMPLETED = 5;
    public static final int ACTION_FAILED = 6;
    public static final int CREDIT_CARD_NOT_FOUND = 7;
    private static Theater theater;

    
    // Private for the singleton pattern
    // Creates the client, show, and customer collection objects
    private Theater() {
      clientList = ClientList.instance();
      customerList = CustomerList.instance();
      showList = ShowList.instance();
    }
    
    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static Theater instance() {
      if (theater == null) {
        CustomerIdServer.instance(); // instantiate all singletons
        return (theater = new Theater());
      } else {
        return theater;
      }
    }
    
    /**
     * Organizes the operations for adding a client
     * @param name client name
     * @param address client address
     * @param phone client phone
     * @return the Client object created
     */
    public Client addClient(String name, String address, String phone) {
      Client client = new Client(name, address, phone);
      if (clientList.insertClient(client)) {
        return (client);
      }
      return null;
    }
    
    /**
    * Organizes the operations for adding a show
    * @param name show name
    * @param clientID client id who owns the show
    * @param period time for the show to be played at the theater
    * @return the Show object created
    */
    public Show addShow(String name, String clientID, Calendar startDate, int period) {
        Show show = new Show(name, clientID, startDate, period);
        if (showList.insertShow(show)) {
          return (show);
        }
        return null;
    }
    
    /**
     * Organizes the operations for adding a customer
     * @param name customer name
     * @param address customer address
     * @param phone customer phone
     * @return the Customer object created
     */
     public Customer addCustomer(String name, String address, String phone,
                                     String creditCardNum, String expiryDate) {
         Customer customer = new Customer(name, address, phone,creditCardNum,expiryDate);
         if (customerList.insertCustomer(customer)) {
             return (customer);
         }
         return null;
     }
     
     /**
      * Organizes the operations for adding a credit card to an existing 
      * customer.
      * If there exists a customer with the given ID, then a credit card
      * will be created, and then added to the customers' card list.
      * 
      * @param customerID owner of the credit card
      * @param cardNumber card number
      * @param expiryDate expiration date of credit card
      * @return creditCard object if added to the CreditCard List, null otherwise.
      */
     public CreditCard addCreditCard(String customerID, String cardNumber, 
    		 							String expiryDate) {
         Customer customer = customerList.search(customerID);
         
         if (customer != null) {
        	 CreditCard creditCard = new CreditCard(customerID, cardNumber, expiryDate);
        	 if (customer.addCreditCard(creditCard) == true) {
        		 return creditCard; // credit card was added successfully
        	 }
        	 return null; // credit card was unable to be added
         }
         return null; // no customer found with given ID
     }
    
    /**
     * Removes a specific client from the ClientList
     * @param clientId id of the client
     * @return a code representing the outcome
     */
    public int removeClient(String clientID) {
        Client client = clientList.search(clientID);
        if (client == null) {
            return(CLIENT_NOT_FOUND);
        }
        if (doesClientHaveUpcomingShow(clientID)) {
        	return(CLIENT_HAS_UPCOMING_SHOW);
        }
        if (clientList.removeClient(clientID)) {
            return (ACTION_COMPLETED);
        }
        return (ACTION_FAILED);
    }
    
    /**
     * Removes a specific customer from the CustomerList
     * @param customertId id of the customer
     * @return a return code representing the outcome of the action
     */
    public int removeCustomer(String customerID) {
        Customer customer = customerList.search(customerID);
        if (customer == null) {
            return(CUSTOMER_NOT_FOUND);
        }
        if (customerList.removeCustomer(customerID)) {
            return (ACTION_COMPLETED);
        }
        return (ACTION_FAILED);
    }
    
    /**
     * Removes a credit card from the collection. If the credit card is
     * the only one the customer has, then credit card cannot be removed.
     * @param customerID customer id owner of the card
     * @param creditCardNumber credit Card number 
     * @return a return code representing the outcome of the action
     */
    public int removeCreditCard(String customerID, String creditCardNumber) {
    	Customer customer = customerList.search(customerID);
    	if (customer == null) {
    		return (CUSTOMER_NOT_FOUND);
    	}
    	if (customer.getNumberOfCards() == 1) {
    		return (CUSTOMER_HAS_ONE_CARD_ONLY);
    	}
    	if (customer.searchCreditCard(creditCardNumber) == null) {
    		return (CREDIT_CARD_NOT_FOUND);
    	}
    	for (Iterator iterator = customer.getCreditCardList(); iterator.hasNext();) {
    		CreditCard card = (CreditCard) iterator.next();
    		if (card.getCardNum().equals(creditCardNumber)) {
    			if (customer.removeCreditCard(creditCardNumber)) {
    				return (ACTION_COMPLETED);
    			}
    		}
    	}
    	return (ACTION_FAILED);
    }
    
    /**
     * Gets an iterator for the client list
     * @return Iterator of ClientList
     */
    public Iterator getClientList() {
    	return clientList.getClients();
    }
    
    /**
     * Gets an iterator for the customer list
     * @return Iterator of CustomerList
     */
    public Iterator getCustomerList() {
    	return customerList.getCustomers();
    }
    
    /**
     * Return list of shows
     * @return iterator to the collection 
     */
    public Iterator getShowList(){
        Iterator shows = showList.getShowList();
        if (shows == null){
            return null;
        } else {
            return shows;
        }
    }
    
    /**
	 * Checks if the client has a current or upcoming show
	 * @return true if the client has a current or upcoming show, false otherwise
	 */
	public boolean doesClientHaveUpcomingShow(String clientID) {
		Calendar currentDate = Calendar.getInstance();
		Iterator iterator = showList.getShowList();
		Show show;
		
		
		// Loops through all shows looking for one by the client that
		// is currently in progress or has a future end date
		while(iterator.hasNext()) {
			show = (Show) iterator.next();
			if (show.getClientId().equals(clientID)
					&& show.getEndDate().after(currentDate)) {
				
				// this show ends after the current date
				return true; // client has a current/future show
			}
		}
		return false; // client has no current/future shows
	}
    
	/**
	 * Determines whether the two given dates are the same. Two dates are the same
	 * if their day, month, and year values are the same. Does not take into account
	 * Timezones, hours, minutes, or seconds.
	 * @param date1 The first date
	 * @param date2 The second date
	 * @return true if the dates are the same, otherwise, false
	 */
	public boolean areDatesEqual(Calendar date1, Calendar date2) {
		if (date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH)
				&& date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)
				&& date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
			return true;
		}
		return false;
	}
	
    /**
	 * Checks if a specified start and end date of a "show to be" is available
	 * @param startDate the starting date of a show
	 * @param endDate the ending date of a show
	 * @return true if the theater is available (no collisions with existing shows) otherwise, false
	 */
    public boolean isTheaterAvailable(Calendar startDate, Calendar endDate){
		Iterator iterator = Theater.instance().getShowList();
		Show show;
		
		while(iterator.hasNext()) {
			show = (Show) iterator.next();
			
			// check if the start or endDate are the same as another shows' start or end date
			if (areDatesEqual(startDate, show.getStartDate())
					|| areDatesEqual(startDate, show.getEndDate())
					|| areDatesEqual(endDate, show.getStartDate())
					|| areDatesEqual(endDate, show.getEndDate())) {
				// the theater is not available for this time.
				// the start or end date conflicts with another show's start or end date
				return false;
			}
			// check if the startDate is between this shows' start and end dates
			if (startDate.after(show.getStartDate()) 
					&& startDate.before(show.getEndDate())) {
			
				// the theater is not available for this time (specified start date is between 
				// another shows' start and end dates)
				return false;
			}
			
			// check if the endDate is between this shows' start and end dates
			if (endDate.after(show.getStartDate()) 
					&& startDate.before(show.getEndDate())) {
				
				// the theater is not available for this time (specified end date is between
				// another shows' start and end dates 
				return false;
			}
			
		}
		// no collisions found with existing shows; theater is available for this date range
		return true;
	}
    
    /**
     * Returns true if ID entered is of an existing client in list.
     * A go-between function for UI function of same name. Main
     * purpose is to be used in addShows to ensure only add shows
     * to existing clients
     * @return true if ID matches existing client in list, else false
     */
    public boolean isValidClient(String clientID) {
    	Client client = clientList.search(clientID);
        if (client == null) {
            return(false);
        }
        else return true;
    }

    /**
     * Retrieves a de-serialized version of the theater from disk
     * @return a Theater object if exists, false otherwise
     */
    public static Theater retrieve() {
      try {
    	File theaterFile = new File("TheaterData");
    	if (theaterFile.exists()) {
    		
    		// the theater file exists, load its' contents and the contents
    		// of the ClientIDServer and CustomerIDServer
	    	FileInputStream file = new FileInputStream(theaterFile);
	        ObjectInputStream input = new ObjectInputStream(file);
	        theater = (Theater) input.readObject();
	        ClientIDServer.retrieve(input);
	        CustomerIdServer.retrieve(input);
	        input.close();
	        file.close();
	        return theater;
    	} else {
    		// the theater file does not exist, return null
    		return null;
    	}
      } catch(IOException ioe) {
        ioe.printStackTrace();
        return null;
      } catch(ClassNotFoundException cnfe) {
        cnfe.printStackTrace();
        return null;
      }
    }
    
    /**
     * Serializes the Theater object
     * @return true if the data could be saved
     */
    public static boolean storeData() {
        try {
            FileOutputStream file = new FileOutputStream("TheaterData");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(theater);
            output.writeObject(ClientIDServer.instance()); 
            output.writeObject(CustomerIdServer.instance());
            output.close();
            file.close();
            return true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }
    
    /** 
     * String form of the theater
     */
    @Override
    public String toString() {
        return "Theater{" + "clientList=" + clientList + ", customerList=" + customerList + ", showList=" + showList + '}';
    }
} // End of class Theater

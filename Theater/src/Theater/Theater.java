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
    public static final int MAX_SEAT_CAPACITY = 40;
    
    public static final int SHOW_NOT_FOUND_ON_DATE = 8;//van:added new case
    public static final int INSUFFICIENT_SEATS_AVAILABLE_ON_DATE = 9;
    public static final int CLIENT_NOT_ENOUGH_FUNDS = 10;
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
    public Show addShow(String name, String clientID, Calendar startDate, int period, double ticketPrice) {
        Show show = new Show(name, clientID, startDate, period,ticketPrice);
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
    	for (Iterator<CreditCard> iterator = customer.getCreditCardList(); iterator.hasNext();) {
    		CreditCard card = (CreditCard) iterator.next();
    		if (card.getCardNum().equals(creditCardNumber)) {
    			if (customer.removeCreditCard(creditCardNumber)) {
    				return (ACTION_COMPLETED);
    			}
    		}
    	}
    	return (ACTION_FAILED);
    }
    
    /*************** TicketFunctionality***********************/
    public int sellStudentAdvanceTicket(Calendar dateOfShow, int ticketNum, String customerID, String
    		creditCardNum) {
    	//customer check
    	Customer customer = customerList.search(customerID);
    	if (customer == null) {
    		return (CUSTOMER_NOT_FOUND);
    	}
    	
    	//credit card check
    	CreditCard creditCard = customer.searchCreditCard(creditCardNum);
        if (creditCard == null) {
            return(CREDIT_CARD_NOT_FOUND);
        }
        
    	//retrieve show from date given
    	Show show = showList.getShowFromShowDate(dateOfShow);
    	//if show not found, get null, pass error to UI - show doesn't play on date
    	if(show == null) {
    		return (SHOW_NOT_FOUND_ON_DATE);
    	}
    	
    	//check if seats available on date
    	if(show.areSeatsAvailableOnDate(dateOfShow, ticketNum)) {
    		//Loop through ticket quantity, gather ticket profit, and add tickets
    		double totalTicketSale = 0.0;
    		for(int i = 0; i < ticketNum; i++) {	
    			StudentAdvancedTicket studentTicket = 
    					new StudentAdvancedTicket(dateOfShow, show.getRegularTicketPrice());
    			
    			//If error on ticket insertion, return error to UI
    			if( !(customer.addTicket(studentTicket)) ) {
    				return (ACTION_FAILED);
    			}
    			//no error so compute ticketPrice sum, and update client's balance
    			else {
    				/*TODO: !!Update the seating structue's deat at date by decrementing -1*/
    				show.updateAvailableSeats(dateOfShow);
    				
    				totalTicketSale+= (studentTicket.getTicketPrice());
    				//update client balance, first retrieve client based on show
    				Client client = clientList.search(show.getClientId());
    				client.updateClientBalance(studentTicket.getTicketPrice()*.5);			
    			}
    		}
    		System.out.printf("(Theater: Action complete! Ticket sale = %.2f IDs\n", totalTicketSale);
    		return (ACTION_COMPLETED);
    	}
    	//No more seats available or number seats less than ticket quantity - return error
    	else {
    		return (INSUFFICIENT_SEATS_AVAILABLE_ON_DATE);
    	} 	
    }
    
    
    public int sellAdvanceTicket(Calendar dateOfShow, int ticketNum, String customerID, String
    		creditCardNum) {
    	//customer check
    	Customer customer = customerList.search(customerID);
    	if (customer == null) {
    		return (CUSTOMER_NOT_FOUND);
    	}
    	
    	//credit card check
    	CreditCard creditCard = customer.searchCreditCard(creditCardNum);
        if (creditCard == null) {
            return(CREDIT_CARD_NOT_FOUND);
        }
        
    	//retrieve show from date given
    	Show show = showList.getShowFromShowDate(dateOfShow);
    	//if show not found, get null, pass error to UI - show doesn't play on date
    	if(show == null) {
    		return (SHOW_NOT_FOUND_ON_DATE);
    	}
    	
    	//check if seats available on date
    	if(show.areSeatsAvailableOnDate(dateOfShow, ticketNum)) {
    		//Loop through ticket quantity, gather ticket profit, and add tickets
    		double totalTicketSale = 0.0;
    		for(int i = 0; i < ticketNum; i++) {	
    			AdvancedTicket advanceTicket = 
    					new AdvancedTicket(dateOfShow, show.getRegularTicketPrice());
    			
    			//If error on ticket insertion, return error to UI
    			if( !(customer.addTicket(advanceTicket)) ) {
    				return (ACTION_FAILED);
    			}
    			//no error so compute ticketPrice sum, and update client's balance
    			else {
    				/*TODO: !!Update the seating structue's deat at date by decrementing -1*/
    				show.updateAvailableSeats(dateOfShow);
    				
    				totalTicketSale+= (advanceTicket.getTicketPrice());
    				//update client balance, first retrieve client based on show
    				Client client = clientList.search(show.getClientId());
    				client.updateClientBalance(advanceTicket.getTicketPrice()*.5);			
    			}
    		}
    		System.out.printf("(Theater: Action complete! Ticket sale = %.2f IDs\n", totalTicketSale);
    		return (ACTION_COMPLETED);
    	}
    	//No more seats available or number seats less than ticket quantity - return error
    	else {
    		return (INSUFFICIENT_SEATS_AVAILABLE_ON_DATE);
    	} 	
    }
    
    public int sellRegularTicket(Calendar dateOfShow, int ticketNum, String customerID, String
    		creditCardNum) {
    	//customer check
    	Customer customer = customerList.search(customerID);
    	if (customer == null) {
    		return (CUSTOMER_NOT_FOUND);
    	}
    	
    	//credit card check
    	CreditCard creditCard = customer.searchCreditCard(creditCardNum);
        if (creditCard == null) {
            return(CREDIT_CARD_NOT_FOUND);
        }
        
    	//retrieve show from date given
    	Show show = showList.getShowFromShowDate(dateOfShow);
    	//if show not found, get null, pass error to UI - show doesn't play on date
    	if(show == null) {
    		return (SHOW_NOT_FOUND_ON_DATE);
    	}
    	
    	//check if seats available on date
    	if(show.areSeatsAvailableOnDate(dateOfShow, ticketNum)) {
    		//Loop through ticket quantity, gather ticket profit, and add tickets
    		double totalTicketSale = 0.0;
    		for(int i = 0; i < ticketNum; i++) {	
    			RegularTicket regularTicket = 
    					new RegularTicket(dateOfShow, show.getRegularTicketPrice());
    			
    			//If error on ticket insertion, return error to UI
    			if( !(customer.addTicket(regularTicket)) ) {
    				return (ACTION_FAILED);
    			}
    			//no error so compute ticketPrice sum, and update client's balance
    			else {
    				/*TODO: !!Update the seating structue's deat at date by decrementing -1*/
    				show.updateAvailableSeats(dateOfShow);
    				
    				totalTicketSale+= (regularTicket.getTicketPrice());
    				//update client balance, first retrieve client based on show
    				Client client = clientList.search(show.getClientId());
    				client.updateClientBalance(regularTicket.getTicketPrice()*.5);			
    			}
    		}
    		System.out.printf("(Theater: Action complete! Ticket sale = %.2f IDs\n", totalTicketSale);
    		return (ACTION_COMPLETED);
    	}
    	//No more seats available or number seats less than ticket quantity - return error
    	else {
    		return (INSUFFICIENT_SEATS_AVAILABLE_ON_DATE);
    	} 	
    }
    
    
    /*************** TicketFunctionality***********************/
    
    public int payClient(Client client, double amount) {
    	if (client.getBalance() < amount) {
    		return CLIENT_NOT_ENOUGH_FUNDS;
    	}
    	if (client.payClient(amount)) {
    		return ACTION_COMPLETED;
    	} else {
    		return ACTION_FAILED;
    	}
    }
    
    /**
     * Gets an iterator for the client list
     * @return Iterator of ClientList
     */
    public Iterator<Client> getClientList() {
    	return clientList.getClients();
    }
    
    /**
     * Gets an iterator for the customer list
     * @return Iterator of CustomerList
     */
    public Iterator<Customer> getCustomerList() {
    	return customerList.getCustomers();
    }
    
    /**
     * Return list of shows
     * @return iterator to the collection 
     */
    public Iterator<Show> getShowList(){
    	return showList.getShowList();
    }
    
    /**
     * Searches for a client from the client list with the specified id
     * @param clientID the clients' id number
     * @return the client if one exists in the list, otherwise null
     */
    public Client searchClient(String clientID) {
    	return clientList.search(clientID);
    }
    
    /**
     * Searches for a customer from the customer list with the specified id
     * @param customerID the customers' id number
     * @return the customer if one exists in the list, otherwise null
     */
    public Customer searchCustomer(String customerID) {
    	return customerList.search(customerID);
    }
    
    /**
     * Searches for a show from the show list with the specified name
     * @param showName the name of the show
     * @return the show if one exists, otherwise null
     */
    public Show searchShow(String showName) {
    	return showList.search(showName);
    }    
    
    /**
	 * Checks if the client has a current or upcoming show
	 * @return true if the client has a current or upcoming show, false otherwise
	 */
	public boolean doesClientHaveUpcomingShow(String clientID) {
		Calendar currentDate = Calendar.getInstance();
		Iterator<Show> iterator = showList.getShowList();
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
		Iterator<Show> iterator = getShowList();
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
	        TicketSerialNumberServer.retrieve(input);
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
            output.writeObject(TicketSerialNumberServer.instance());
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


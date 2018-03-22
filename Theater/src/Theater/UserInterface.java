package Theater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * 
 * This class implements the user interface for the Theater project.
 * The commands are encoded as integers using a number of
 * static final variables. A number of utility methods exist to
 * make it easier to parse the input.
 *
 * @author Vanessa Esaw, David Jaqua, Franklin Ortega
 */
public class UserInterface {
    private static UserInterface userInterface;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Theater theater;
    private static final int EXIT = 0;
    private static final int ADD_CLIENT = 1;
    private static final int REMOVE_CLIENT = 2;
    private static final int LIST_CLIENTS = 3;
    private static final int ADD_CUSTOMER = 4;
    private static final int REMOVE_CUSTOMER = 5;
    private static final int ADD_CREDIT_CARD = 6;
    private static final int REMOVE_CREDIT_CARD = 7;
    private static final int LIST_CUSTOMERS = 8;
    private static final int ADD_SHOW = 9;
    private static final int LIST_SHOWS = 10;
    private static final int STORE_DATA = 11;
    private static final int RETRIEVE_DATA = 12;
    private static final int SELL_REGULAR_TICKETS = 13;
    private static final int SELL_ADVANCED_TICKETS = 14;
    private static final int SELL_STUDENT_ADVANCED_TICKETS = 15;
    private static final int PAY_CLIENT = 16;
    private static final int PRINT_ALL_TICKETS_ON_DAY = 17;
    private static final int HELP = 18; 
    
    //Variable used below exclusively in advancedTicketPurchasing methods
    private static final int PURCHASE_DATE_NOT_IN_ADVANCE = -1;
    
    /**
     * Made private for singleton pattern.
     * Conditionally looks for any saved data. Otherwise, it gets
     * a singleton Theater object.
     */
    private UserInterface() {
      if (yesOrNo("Look for saved data and use it?")) {
        retrieveData();
      } else {
        theater = Theater.instance();
      }
    }
    
    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static UserInterface instance() {
      if (userInterface == null) {
        return userInterface = new UserInterface();
      } else {
        return userInterface;
      }
    }
    
    /**
     * Gets a token after prompting
     * 
     * @param prompt - whatever the user wants as prompt
     * @return - the token from the keyboard
     * 
     */
    public String getToken(String prompt) {
      do {
        try {
          System.out.println(prompt);
          String line = reader.readLine();
          StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
          if (tokenizer.hasMoreTokens()) {
            return tokenizer.nextToken();
          }
        } catch (IOException ioe) {
          System.exit(0);
        }
      } while (true);
    }
    
    /**
     * Queries for a yes or no and returns true for yes and false for no
     * 
     * @param prompt The string to be prepended to the yes/no prompt
     * @return true for yes and false for no
     * 
     */
    private boolean yesOrNo(String prompt) {
      String more = getToken(prompt + " (Y|y)[es] or anything else for no");
      return !(more.charAt(0) != 'y' && more.charAt(0) != 'Y');
    }
    
    /**
     * Converts the string to a number
     * @param prompt the string for prompting
     * @return the integer corresponding to the string
     * 
     */
    public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
    }
    
    /**
     * Converts the string to a double number
     * @param prompt the string for prompting
     * @return the double corresponding to the string
     * 
     */
    public double getDouble(String prompt) {
		do {
			try {
				String item = getToken(prompt);
                Double number = Double.valueOf(item);
                return number;
			} catch (NumberFormatException nfe) {
				System.out.println("Please input an amount ##.## ");
                return 0;
			}
		} while (true);
    }
    
    /**
     * Prompts for a date and gets a date object
     * @param prompt the prompt for the user
     * @return the data as a Calendar object if date is valid, otherwise null (not valid date)
     */
    public Calendar getDate(String prompt) {
    	String item;
    	while (true) {
    		item = getToken(prompt);
    		if (item.contains("/")) {
    			if (item.split("/").length == 3) {
    				break; // valid date length, proceed to calculating validity
    			}
    			System.out.println("Invalid date. Must contain -'s. "
    					+ "Formatted like MM/DD/YYYY");
    		} else {
    			System.out.println("Invalid date. Must contain -'s. "
    					+ "Formatted like MM/DD/YYYY");
    		}
    	}
    	String[] parts = item.split("/");
    	Calendar date = Calendar.getInstance();
    	date.clear();
    	date.setLenient(false);
    	date.set(Integer.valueOf(parts[2]), 
    			Integer.valueOf(parts[0])-1, // month is 0 index so we subtract 1 from the given integer
    			Integer.valueOf(parts[1])); // sets the year, month, day values for the calendar
    	try {
    		date.getTime(); // will throw an exception if the date given is not valid
    	} catch(Exception e) {
    		System.out.println("Invalid date");
    		return null;
    	}
    	return date;
    }
    
    /**
     * Prompts for a command from the keyboard
     * 
     * @return a valid command
     * 
     */
    public int getCommand() {
      do {
        try {
          int value = Integer.parseInt(getToken("Enter command: "));
          if (value >= EXIT && value <= HELP) {
            return value;
          }
        } catch (NumberFormatException nfe) {
          System.out.println("Enter a number");
        }
      } while (true);
    }
    
    /**
     * Displays the help screen
     * 
     */
    public void help() {
      System.out.println("Enter a number between 0 and 18 as explained below:");
      System.out.println(EXIT + " to Exit");
      System.out.println(ADD_CLIENT + " to add a client");
      System.out.println(REMOVE_CLIENT + " to remove client");
      System.out.println(LIST_CLIENTS + " to list all clients");
      System.out.println(ADD_CUSTOMER + " to add a customer");
      System.out.println(REMOVE_CUSTOMER + " to remove a customer");
      System.out.println(ADD_CREDIT_CARD + " to add a credit card");
      System.out.println(REMOVE_CREDIT_CARD + " to remove a credit card");
      System.out.println(LIST_CUSTOMERS + " to list all customers");
      System.out.println(ADD_SHOW + " to add a show/play");
      System.out.println(LIST_SHOWS + " to list all shows");
      System.out.println(STORE_DATA + " to save data");
      System.out.println(RETRIEVE_DATA + " to retrieve data");
      System.out.println(SELL_REGULAR_TICKETS + " to buy regular-priced tickets");
      System.out.println(SELL_ADVANCED_TICKETS + " to buy advance tickets");
      System.out.println(SELL_STUDENT_ADVANCED_TICKETS + " to buy student advance tickets");
      System.out.println(PAY_CLIENT + " to payout client");
      System.out.println(PRINT_ALL_TICKETS_ON_DAY + " to print all tickets of a particular day");
      System.out.println(HELP + " for help");
    }
    
    /**
     * Method to be called for adding a client.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for adding the client.
     *  
     */
    public void addClient() {
        String name = getToken("Enter client name");
        String address = getToken("Enter address");
        String phone = getToken("Enter phone");
        Client result;
        result = theater.addClient(name, address, phone);
        if (result == null) {
            System.out.println("Could not add client");
        }
        System.out.println(result);
    }
    
    /**
     * Method to be called for adding a customer.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for adding the client
     */
    public void addCustomer() {
        String name = getToken("Enter customer name");
        String address = getToken("Enter address");
        String phone = getToken("Enter phone");
        String creditCard = getToken("Enter credit card number");
        String expiryDate = getToken("Enter Credit Card expiration date");
        Customer result;
        result = theater.addCustomer(name, address, phone, creditCard, expiryDate);
        if (result == null) {
            System.out.println("Could not add customer");
        }
        System.out.println(result);
    }
    
    /**
     * Method to be called for adding a show.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for adding only if theater is free 
     * during this period of the show.
     */
    public void addShows() {
    	Show result;
        do {
            String clientID = getToken("Enter client id"); 
            if(!theater.isValidClient(clientID)) {
            	System.out.println("Invalid client ID entered.");
            } else {
            	Calendar startDate;
                double ticketPrice;
                String name = getToken("Enter Show name");
            	while (true) {
            		startDate = getDate("Enter start date of the show(MM/DD/YYYY)");
            		if (startDate != null) {
            			break; // valid date entered
            		} else {
            			System.out.println("Invalid Date. Try again.");
            		}
            	}
                int period = getNumber("Enter duration of the show (in days)");
            	//if price is correct then can continue add the show 
                while (true) {
                        ticketPrice = getDouble("Enter regular ticket price of the show");
            		if (ticketPrice != 0) {
            			break; // valid date entered
            		} else {
            			System.out.println("Invalid Price. Try again.");
            		}
            	}

                // check if the theater is available for this range of dates
                Calendar endDate = (Calendar) startDate.clone();
                endDate.add(Calendar.DAY_OF_MONTH, period);
                if (Theater.instance().isTheaterAvailable(startDate, endDate)) {
                        // all go for creating the show
                        result = theater.addShow(name, clientID, startDate, period, ticketPrice);
                        if (result != null) {
                                System.out.println(result);
                        } else {
                                System.out.println("Show could not be added");
                        }
                } else {
                        System.out.println("Theater is not available for the timeframe");
                        System.out.println(Show.dateToString(startDate) + " TO " 
                                                                + Show.dateToString(endDate));
                }
            }
            if (!yesOrNo("Add more shows?")) {
                break;
            }
        } while (true);
    }
    
    /**
     * Method to be called for adding a credit card.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for adding the credit card.
     * If credit card on file, adding credit card to list fails.
     */
    public void addCreditCard() {
        String customerId = getToken("Enter Customer id");
        String creditCardNumber = getToken("Enter Credit Card Number");
        String expiryDate = getToken("Enter card expiration date (mm/dd/yy)");
        // Validating input
        if (doesCardAlreadyExist(creditCardNumber)){
        	System.out.println("Credit Card already in file under a different "
                    + "customer.");
        } else{
            CreditCard result = theater.addCreditCard(customerId, creditCardNumber, expiryDate);
            if (result != null){
                System.out.println(result.toString());
            } else{
                System.out.println("Card was unable to be added.");
            }
        }
    }
    
	/**
	 * Method to be called for removing clients. Removes clients only if client
	 * has no current show playing or upcoming shows.
	 * Prompts the user for the appropriate values and
	 * uses the appropriate Theater method for removing clients.
	 *  
	 */
    public void removeClient() {
        int result;
        do {
            String clientID = getToken("Enter client id:");
            result = theater.removeClient(clientID);
            switch(result){
                case Theater.CLIENT_NOT_FOUND:
                  System.out.println("Client not found in Client List");
                  break;
                case Theater.CLIENT_HAS_UPCOMING_SHOW:
                  System.out.println("Client could not be removed, it "
                          + "has a current show playing or upcoming shows.");
                  break;
                case Theater.ACTION_FAILED:
                  System.out.println("Client could not be removed.");
                  break;
                case Theater.ACTION_COMPLETED:
                    System.out.println("Client has been removed");
                    break;
                default:
                  System.out.println("An error has occurred");
            }
            if (!yesOrNo("Remove more clients?")) {
              break;
            }
        } while (true);
    }
    
    /**
     * Method to be called for removing customers.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for removing customers.
     * Catches return code to prompt to the user.
     */
    public void removeCustomer() {
        // result: int variable to save remove result
        // DoWhile loop for removing customers
        // String: Use getToken() to get Customer Id from user input
        // result = removing customer from list
        // Switch case based on result
        int result;
        do {
            String customerID = getToken("Enter Customer Id:");
            result = theater.removeCustomer(customerID);
            switch(result){
                case Theater.CUSTOMER_NOT_FOUND:
                    System.out.println("Customer not found in Customer List.");
                    break;
                case Theater.ACTION_COMPLETED:
                    System.out.println("Customer has been removed.");
                    break;
                case Theater.ACTION_FAILED:
                    System.out.println("Could not remove Customer.");
                    break;
                default: 
                    System.out.println("An error has ocurred.");
            }
            if(!yesOrNo("Remove nore customers?")){
                break;
            }
        } while (true);
    }
    
   /**
    * Method to be called for removing a customer's credit card. 
    * It removes a credit card if customer has more than one credit card.
    * Prompts user for id and card number, and uses appropriate theater method 
    * for removing that customers' credit card.
    * Catches return code to display to the user.
    */
    public void removeCreditCard() {
    	String customerID = getToken("Enter Customer Id:");
    	String creditCardNumber = getToken("Enter Credit Card Number:");
    	int result = theater.removeCreditCard(customerID, creditCardNumber);
    	
    	switch(result) {
    		case Theater.ACTION_COMPLETED:
    			System.out.println("Customers' credit card was removed.");
    			break;
    		case Theater.CUSTOMER_NOT_FOUND:
    			System.out.println("Customer not found in Customer List.");
    			break;
    		case Theater.CUSTOMER_HAS_ONE_CARD_ONLY:
    			System.out.println("Customer has only one credit card. "
    					+ "All customers must have atleast one card.");
    			break;
    		case Theater.CREDIT_CARD_NOT_FOUND:
    			System.out.println("The customer does not have a credit card with "
    					+ "that number.");
    			break;
    		case Theater.ACTION_FAILED:
    			System.out.println("Could not remove customers' Credit Card");
    			break;
    		default: 
                System.out.println("An error has ocurred.");
    	}
    }
    
    /**
     * Method to be called for listing all clients.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for displaying client list.
     *  
     */
    public void getClients() {
        Iterator<Client> iterator = theater.getClientList();
        if (!iterator.hasNext()) {
        	// hasNext returns false if there are no contents in the list
            System.out.println("Client List is empty.");
        } else {
            while(iterator.hasNext()) {
                Client client = (Client) iterator.next();
                System.out.println(client.toString());
            }
            System.out.println("\n**There are no more clients** \n" );
        }
    }
    
    /**
     * Method to be called for listing all customers.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for displaying customer list.
     *  
     */
    public void getCustomers() {
        Iterator<Customer> iterator = theater.getCustomerList();
        if (!iterator.hasNext()) {
        	// hasNext returns false if there are no contents in the list
            System.out.println("Customer List is empty.");
        } else {
            while(iterator.hasNext()) {
                Customer customer = (Customer) iterator.next();
                System.out.println(customer.toString());
            }
            System.out.println("\n**There are no more customers**\n" );
        }
    }
    
    /**
     * Method to be called for displaying shows.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for displaying shows.
     *  
     */
    public void getShows() {
      Iterator<Show> iterator = theater.getShowList();
        if (!iterator.hasNext()) {
        	// hasNext returns false if there are no contents in the list
            System.out.println("Show List is empty.");
        } else {
            while(iterator.hasNext()) {
                Show show = (Show) iterator.next();
                System.out.println(show.toString());
            }
            System.out.println("\n**There are no more shows**\n" );
        }
    }
    
    /*************** TicketFunctionality***********************/
    /**
     * Method undergoes process of purchasing a student advance ticket.
     * It first checks if the date of purchase (current date) is prior to the date of 
     * the show. If this is true, it calls theaters sellStudentAdvanceTickets() function
     * */
    public void sellStudentAdvanceTicket() {
    	String customerID = getToken("Enter Customer Id:");
    	int ticketNum = getNumber("Enter number of student tickets to buy:");	
    	System.out.printf("[Prompt customer] please show %d valid Student IDs\n", ticketNum);
    	String creditCard = getToken("Enter credit card number");
    	
    	Calendar showDate;
        while (true) {
        	showDate = getDate("Enter date of show tickets to purchase(MM/DD/YYYY)");
    		if (showDate != null) {
    			break; // valid date entered
    		} else {
    			System.out.println("Invalid Date. Try again.");
    		}
    	}
        
        
        //prior check made by UI - check purchase date (current) is before show date
        int result;
        //If purchase date is prior, call theater function
        if(isPurchaseDateInAdvance(showDate)) {
        	result = theater.sellStudentAdvanceTicket(showDate, ticketNum, 
          		   customerID, creditCard);
        } 
        //Else, theater date not in advanced so set result to error sentinel
        else {
        	result = PURCHASE_DATE_NOT_IN_ADVANCE;
        }
       
        switch(result){ 
        case PURCHASE_DATE_NOT_IN_ADVANCE:
        	System.out.println("Date of purchase is not before show date."
        			+ "\nCannot purchase student advance tickets");
        	break;
        case Theater.CUSTOMER_NOT_FOUND:
        	System.out.println("Customer not found in Customer List.");
        	break;
               
        case Theater.CREDIT_CARD_NOT_FOUND:
        	System.out.println("The customer does not have a credit card with "
					+ "that number.");                
        	break;
        	
        case Theater.SHOW_NOT_FOUND_ON_DATE:
        	System.out.println("No show plays on that date.");
        	break;
        	
        case Theater.ACTION_FAILED:
        	System.out.println("Could not purchase a student advance ticket "
        			+ "\n(error in insertion of ticket int cutomer object\n)");
        	break;
        	
        case Theater.ACTION_COMPLETED:
        	System.out.println(ticketNum + " Student advance discount tickets have been purcased");
        	break;
        	
        case Theater.INSUFFICIENT_SEATS_AVAILABLE_ON_DATE:
        	System.out.println("Insufficient amount of seats on date");
        	break;
   	
        default:
        	System.out.println("An error has occurred");
        	}
    	
    }
    
    /**
     * Method to be called for displaying tickets for a day.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for displaying shows.
     *  
     */
    public void printAllTicketsOnDay() {
        Iterator <Ticket> result;
        Calendar date = Calendar.getInstance();
        date = getDate("Enter date (MM/DD/YYYY)");
        
        result = theater.getTicketsOnDate(date);
        if (result == null){
            System.out.println("No tickets in this date");
        } else {
            while(result.hasNext()){
                Ticket ticket = (Ticket) result.next();
                System.out.println(ticket.toString());
            }
            System.out.println("\n**There are no more tickets on this date**\n" );
        }
    }
    
    /**
     * Method undergoes process of purchasing an advance ticket.
     * It first checks if the date of purcase (current date) is prior to the date of 
     * the show. If this is true, it calls theaters sellStudentAdvanceTickets() function
     * */
    public void sellAdvanceTicket() {
    	String customerID = getToken("Enter Customer Id:");
    	int ticketNum = getNumber("Enter number of advance tickets to buy:");
    	String creditCard = getToken("Enter credit card number");
    	
    	Calendar showDate;
        while (true) {
        	showDate = getDate("Enter date of show tickets to purchase(MM/DD/YYYY)");
    		if (showDate != null) {
    			break; // valid date entered
    		} else {
    			System.out.println("Invalid Date. Try again.");
    		}
    	}
        
        //prior check made by UI - check purchase date (current) is before show date
        int result;
        //If purchase date is prior, call theater function
        if(isPurchaseDateInAdvance(showDate)) {
        	result = theater.sellAdvanceTicket(showDate, ticketNum, 
          		   customerID, creditCard);
        } 
        //Else, theater date not in advanced so set result to error sentinel
        else {
        	result = PURCHASE_DATE_NOT_IN_ADVANCE;
        }
       
        switch(result){ 
        case PURCHASE_DATE_NOT_IN_ADVANCE:
        	System.out.println("Date of purchase is not before show date."
        			+ "\nCannot purchase advance tickets");
        	break;
        case Theater.CUSTOMER_NOT_FOUND:
        	System.out.println("Customer not found in Customer List.");
        	break;
               
        case Theater.CREDIT_CARD_NOT_FOUND:
        	System.out.println("The customer does not have a credit card with "
					+ "that number.");                
        	break;
        	
        case Theater.SHOW_NOT_FOUND_ON_DATE:
        	System.out.println("No show plays on that date.");
        	break;
        	
        case Theater.ACTION_FAILED:
        	System.out.println("Could not purchase a  advance ticket "
        			+ "\n(error in insertion of ticket int cutomer object\n)");
        	break;
        	
        case Theater.ACTION_COMPLETED:
        	System.out.println(ticketNum + " Advance discount tickets have been purcased");
        	break;
        	
        case Theater.INSUFFICIENT_SEATS_AVAILABLE_ON_DATE:
        	System.out.println("Insufficient amount of seats on date");
        	break;
   	
        default:
        	System.out.println("An error has occurred");
        	}
    	
    }
    
    /**
     * Method undergoes process of purchasing a regular ticket.
     * It first checks if the date of purchase is the current date of the show. 
     * If this is true, it calls theater's sellStudentAdvanceTickets() function
     * 
     * */
    public void sellRegularTicket() {String customerID = getToken("Enter Customer Id:");
	int ticketNum = getNumber("Enter number of regular tickets to buy:");
	String creditCard = getToken("Enter credit card number");
    
    Calendar showDate;
    while (true) {
    	showDate = getDate("Enter date of show tickets to purchase(MM/DD/YYYY)");
		if (showDate != null) {
			break; // valid date entered
		} else {
			System.out.println("Invalid Date. Try again.");
		}
	}
	
    int result = theater.sellRegularTicket(showDate, ticketNum, customerID, creditCard);
    switch(result){ 
    case Theater.CUSTOMER_NOT_FOUND:
    	System.out.println("Customer not found in Customer List.");
    	break;
           
    case Theater.CREDIT_CARD_NOT_FOUND:
    	System.out.println("The customer does not have a credit card with "
				+ "that number.");                
    	break;
    	
    case Theater.SHOW_NOT_FOUND_ON_DATE:
    	System.out.println("No show plays on that date.");
    	break;
    	
    case Theater.ACTION_FAILED:
    	System.out.println("Could not purchase a regular ticket "
    			+ "\n(error in insertion of ticket int cutomer object\n)");
    	break;
    	
    case Theater.ACTION_COMPLETED:
    	System.out.println(ticketNum + " Regular tickets have been purcased");
    	break;
    	
    case Theater.INSUFFICIENT_SEATS_AVAILABLE_ON_DATE:
    	System.out.println("Insufficient amount of seats on date");
    	break;
	
    default:
    	System.out.println("An error has occurred");
    	}
    }
    
    /**
     * Method to be called to pay the client.
     * Uses the appropriate Theater method for complying the task.
     *  
     */
    public void payClient() {
    	String clientID = getToken("Enter Client ID:");
    	Client client = theater.searchClient(clientID);
    	if (client == null) {
    		System.out.println("Client does not exist with that ID.");
    		return;
    	}
    	double currentBalance = client.getBalance();
    	System.out.println("The clients' current balance is " + currentBalance);
    	double amount = getDouble("Enter amount to payout:");
    	if (amount < 0) {
    		System.out.println("Cannot payout client negative amount.");
    		return;
    	}
    	int result = theater.payClient(client, amount);
    	
    	switch (result) {
    		case Theater.CLIENT_NOT_ENOUGH_FUNDS:
    			System.out.println("The client does not have enough funds for that.");
    			break;
    		case Theater.ACTION_COMPLETED:
    			System.out.println(amount + " has been deducted from the clients' account.");
    			System.out.println("The clients new balance is " + (currentBalance - amount));
    			break;
    		case Theater.ACTION_FAILED:
    			System.out.println("Could not deduct balance from the clients' account.");
    			break;
    		default:
            	System.out.println("An error has occurred");
    	}
    }
    
    /**
     * Method to be called for saving the Theater object.
     * Uses the appropriate Theater method for saving.
     *  
     */
    private void storeData() {
        if (Theater.storeData()) {
            System.out.println("The theater has been successfully saved "
                    + "in the file \"TheaterData\" \n" );
        } else {
            System.out.println("There has been an error in saving Theater data \n" );
        }
    }
    
    /**
     * Method to be called for retrieving saved data.
     * Uses the appropriate Theater method for retrieval.
     *  
     */
    private void retrieveData() {
      try {
        Theater tempTheater = Theater.retrieve();
        if (tempTheater != null) {
          System.out.println("The theater has been successfully retrieved "
                  + "from the file TheaterData \n" );
          theater = tempTheater;
        } else {
          System.out.println("File doesnt exist; creating new theater \n" );
          theater = Theater.instance();
        }
      } catch(Exception cnfe) {
        cnfe.printStackTrace();
      }
    }
    
   /**
    * Checks if the given card already exists in the database
    * @return true if the card already exists, else false.
    */
    private boolean doesCardAlreadyExist(String creditCardNumber) {
        // Loops through all customers looking to see if any of them already
        // have a credit card with the given number.
        Iterator<Customer> custIterator = theater.getCustomerList();
        while (custIterator.hasNext()) {
        	Customer customer = (Customer) custIterator.next();
    		Iterator<CreditCard> credIterator = customer.getCreditCardList();
    		while (credIterator.hasNext()) {
    			CreditCard creditCard = (CreditCard) credIterator.next();
    			if (creditCard.getCardNum().equals(creditCardNumber)) {
    				return true; // a customer already has a card with this number
    			}
    		}
        }
        return false; // no customers found with the given card number
    }
    
    /**
     * Checks if the date given is before that date of the show. To be used in 
     * both sellAdvancedTicket() and sellStudentAdvancedTicket() classes
     * 
     * @return true if show date is prior to the current day, else false
     */
    private boolean isPurchaseDateInAdvance(Calendar dateOfShow) {
    	Calendar currentDate = Calendar.getInstance();
    	/*System.out.printf("isPurchaseDateInAdvance: current date = %s",
    			dateFormat.format(currentDate));*/
    	
    	if(currentDate.before(dateOfShow))
    		return true;
    	else return false;
    		
    }
    
    /**
     * Orchestrates the whole process.
     * Calls the appropriate method for the different functionalities.
     *  
     */
    public void process() {
      int command;
      help();
      while ((command = getCommand()) != EXIT) {
        switch (command) {
          case ADD_CLIENT:        addClient();
                                  break;
          case REMOVE_CLIENT:     removeClient();
                                  break;
          case LIST_CLIENTS:      getClients();
                                  break;
          case ADD_CUSTOMER:      addCustomer();
                                  break;
          case REMOVE_CUSTOMER:   removeCustomer();
                                  break;
          case ADD_CREDIT_CARD:   addCreditCard();
                                  break;
          case REMOVE_CREDIT_CARD:removeCreditCard();
                                  break;
          case LIST_CUSTOMERS:    getCustomers();
                                  break;
          case ADD_SHOW:          addShows();
                                  break;
          case LIST_SHOWS:        getShows();
                                  break;
          case STORE_DATA:        storeData();
                                  break;
          case RETRIEVE_DATA:     retrieveData();
                                  break;
          case HELP:              help();
                                  break;
          case 
          SELL_STUDENT_ADVANCED_TICKETS:  
        	  					  sellStudentAdvanceTicket();
          						  break;          
          case
          SELL_ADVANCED_TICKETS:  sellAdvanceTicket();
          						  break;
          case
          SELL_REGULAR_TICKETS:  sellRegularTicket();
          						  break;  
          case 
          PRINT_ALL_TICKETS_ON_DAY:  printAllTicketsOnDay();
          						  break;
          case PAY_CLIENT:
        	  					  payClient();
        	  					  break;
        }
      }
      storeData();
    }
    /**
     * The method to start the application. Simply calls process().
     * @param args not used
     */
    public static void main(String[] args) {
        UserInterface.instance().process();
    }
}

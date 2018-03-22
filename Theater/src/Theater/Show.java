package Theater;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/** 
 * Show class represents a show/play which is held in theater on a range of days
 * It has a show name, the client id for the client who owns the show, date for
 * show release, and a date for the show finale.
 *
 * @author Franklin Ortega & David Jaqua
 */
public class Show implements Matchable<String>, Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String clientID;
        private int duration;
	private Calendar startDate;
	private Calendar endDate;
	private double regularTicketPrice;
        private Map <Integer, Integer> seatingAvailable;

	/**
	 * Creates a new show with the given name, clientID, start year, month, day, and the
	 * duration of showing in days.
	 * 
	 * <br><br>
	 * 
	 * <b>WARNING!</b> This does not check if the show can be held within this range of days!
	 * The class that uses this show must check before inserting this show into the ShowList using
	 * isTheaterAvailable()
	 * 
	 * @param name name of the show
	 * @param clientID the owner of the show
	 * @param startDate is the start date of the show
	 * @param duration the number of days the show will be held
         * @param ticketPrice regular ticket price for the show
	 */
	public Show(String name, String clientID, Calendar startDate, int duration, double ticketPrice) {
		this.name = name;
		this.clientID = clientID;
                this.duration = duration;
                
		/*
		 * month is subtracted by 1 because the month is 0-based (0 = January)
		 */
		this.startDate = Calendar.getInstance(); // get new calendar instance
                this.startDate.clear(); 
                this.startDate.set(startDate.get(Calendar.YEAR), 
                                startDate.get(Calendar.MONTH), 
                                startDate.get(Calendar.DAY_OF_MONTH));
                /*
                 * determines the end date by adding the duration of days onto the start date
                 */
                this.endDate = Calendar.getInstance(); // get new calendar instance
                this.endDate.clear(); // clear the calendars existing values
                this.endDate.set(startDate.get(Calendar.YEAR), 
                                startDate.get(Calendar.MONTH), 
                                startDate.get(Calendar.DAY_OF_MONTH));
                // aDuration of show is subtractec 1 because start Date is 
                // one of the days the show is playing
                this.endDate.add(Calendar.DATE, (duration -1)); 

                // Creates seating capacity for each day of the show 
                seatingAvailable = new HashMap<>();
                seatingAvailable = assingSeatsToDate(startDate, duration);
                
                
                this.regularTicketPrice = ticketPrice;
	}

	/**
	 * Gets the shows' name
	 * @return The shows' name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the shows' owners' client id
	 * @return The clients' id
	 */
	public String getClientId() {
		return clientID;
	}

	/**
	 * Gets the start date of the show
	 * @return calendar representation of the start date
	 */
	public Calendar getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Gets the end date of the show
	 * @return calendar representation of the end date
	 */
	public Calendar getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the shows' name
	 * @param name New name of the show
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the shows' clients' id
	 * @param clientID the new client ID
	 */
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	/**
	 * Sets the start date of the show
	 * <br><br>
	 * <b>WARNING!</b> This does not check if the show can be held within this new range of days!
	 * The class that uses this show must check before inserting this show into the ShowList using
	 * isTheaterAvailable()
	 * 
	 * @param startDate The new start date
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Sets the end date of the show
	 * <br><br>
	 * <b>WARNING!</b> This does not check if the show can be held within this new range of days!
	 * The class that uses this show must check before inserting this show into the ShowList using
	 * isTheaterAvailable()
	 * 
	 * @param endDate The new end date
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
        
    /**
     * Checks if show has available seats for a specific date.  
     * 
     * @param date a specific date of the show 
     * @param ticketsQuantity the amount of tickets that would be purchased 
     * @return true if ticketsQuantity is less than the show seating availability.
     */
    public boolean areSeatsAvailableOnDate(Calendar date, int ticketsQuantity){
            return getSeatingAvailableOnDate(date) > ticketsQuantity;
        }
    
    /**
     * Goes through seatingAvailable hash table to find the amount of seating 
     * available on a specific date.
     * 
     * @param date used to get seating availability 
     * @return number of seats available 
     */
    public int getSeatingAvailableOnDate(Calendar date) {
        int seats = 0;
        int dateKey = createHashKeyFromDate(date);
        
            if (seatingAvailable.containsKey(dateKey)){
                seats = seatingAvailable.get(dateKey);
            } 
        
        return seats;
    }

    /**
     *
     * @param SeatingAvailable
     */
    public void setSeatingAvailable(Map<Integer, Integer> SeatingAvailable) {
        this.seatingAvailable = SeatingAvailable;
    }
        
        /**
         *
         * @return regular ticket price of the show
         */
        public double getRegularTicketPrice() {
            return regularTicketPrice;
        }
    
    
    
	@Override
	public String toString(){
		return "Show [name=\"" + name + "\" clientID=" + clientID 
				+ " startDate=" + dateToString(this.startDate) 
				+ " endDate=" + dateToString(this.endDate) 
                                + " regularPrice=" + this.regularTicketPrice
                                + "\nseating:" + seatingAvailableToString(this.seatingAvailable) + "]";
	}
	
	/**
	 * Gets a custom toString value of a java Calendar date
	 * Since month is 0 based, 1 is added to month for display purposes
	 * 
	 * @param date Java Calendar date to convert
	 * @return String representation of the date
	 */
	public static String dateToString(Calendar date) {
		return "Calendar [year=" + date.get(Calendar.YEAR) 
				+ " month=" + (date.get(Calendar.MONTH) + 1) 
				+ " day=" + date.get(Calendar.DAY_OF_MONTH) + "]";
	}
        
    /**
     * Gets a custom toString value of date and available seating 
     * 
     * @param seatingAvailable
     * @return
     */
    private Map seatingAvailableToString(Map<Integer, Integer> seatingAvailable) {
            Map<String, String> seats = new HashMap<>();
            Calendar tempDate = Calendar.getInstance();
            // duration
            tempDate.set(this.startDate.get(Calendar.YEAR), 
                         this.startDate.get(Calendar.MONTH),
                         this.startDate.get(Calendar.DAY_OF_MONTH));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String formatedDate;
            
            for (int i = 0; i < this.duration; i++) {
                formatedDate = dateFormat.format(tempDate.getTime());
                seats.put(formatedDate, String.valueOf(seatingAvailable.get(createHashKeyFromDate(tempDate))));
                tempDate.add(Calendar.DATE, 1);
            }
            return seats;
        }
	
        /**
        * Subtract 1 seat from a specific show seating capacity. 
        * 
        * @param date of the show where seat availability needs to be updated.
        * @return true if available seats have been subtracted by 1, false otherwise. 
        */
        public boolean updateAvailableSeats(Calendar date){
            int seatingKey = createHashKeyFromDate(date);
            int keyValue = getSeatingAvailableOnDate(date);
            Map <Integer, Integer> tempSeatsAvailable  = new HashMap<>();
            tempSeatsAvailable.clear();
            
            return (this.seatingAvailable.put(seatingKey, (keyValue - 1))) < keyValue;
        }
        
	/**
	 * Checks if this show is the same as another show with the given
	 * show name
	 * @param showName the name of another show
	 * @return true if the shows are the same, otherwise false
	 */
	@Override
	public boolean matches(String showName) {
		return name.equals(showName);
	}
        
        /**
         * Gets the date's year and date's day of the year, add those two integers
         * to create the "Key" for the seating available hash table.
         * @param date to be converted into a integer value
         * @return the sum of the integers: year of date number + day of year number
         */
        private int createHashKeyFromDate(Calendar date){
            int startYearNum = date.get(Calendar.YEAR);
            int monthNum = date.get(Calendar.MONTH);
            int dayNum = date.get(Calendar.DAY_OF_YEAR);
            int keyNumber = startYearNum + monthNum + dayNum;
            
            return keyNumber;
        }
        
        /**
         * Seating format: Map <Integer,Integer> seatingAvailable;
         * Gets Integer from show-start-date to set seatingAvailable "key" and
         * gets Integer from theater seating capacity to set each seatingAvailable "Value" 
         * 
         * @param startDate
         * @param duration
         * @return 
         */
        private Map<Integer, Integer> assingSeatsToDate(Calendar startDate, int duration) {
            int theaterSeatCapacity = Theater.MAX_SEAT_CAPACITY;
            Map <Integer, Integer> tempSeatingAvailable = new HashMap<>();
            int hashTableKey; 


            for (int i = 1; i <= duration; i++) {
                hashTableKey = createHashKeyFromDate(startDate);
                tempSeatingAvailable.put(hashTableKey, theaterSeatCapacity);
                startDate.add(Calendar.DATE, 1);
            }

            return tempSeatingAvailable;
        }
}
package Theater;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;

/** 
 * Show class represents a show/play which is held in theater on a range of days
 * It has a show name, the client id for the client who owns the show, date for
 * show release, and a date for the show finale.
 *
 * @author Franklin Ortega & David Jaqua
 */
public class Show implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String clientID;
	private Calendar startDate;
	private Calendar endDate;

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
	 */
	public Show(String name, String clientID, Calendar startDate, int duration) {
		this.name = name;
		this.clientID = clientID;
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
        this.endDate.add(endDate.DATE, duration);
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
	
	@Override
	public String toString(){
		return "Show [name=\"" + name + "\" clientID=" + clientID 
				+ " startDate=" + dateToString(this.startDate) 
				+ " endDate=" + dateToString(this.endDate) + "]";
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
	
}
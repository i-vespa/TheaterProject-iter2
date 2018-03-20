package Theater;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;

/** 
 * ShowList class is a singleton class that holds the list of shows that
 * are inside the system. Methods such as inserting, searching,
 * and deleting shows from the list.
 *
 * @author Franklin Ortega
 * Date: Feb 23, 2018
 */
public class ShowList extends GenericList<Show, String> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static ShowList showListInstance;

    private ShowList() {
    }

    /**
     * Implements the singleton class of ShowList.
     * 
     * @return new instance of singleton class if doesn't exists, otherwise
     *          returns an existing one.
     */
    public static ShowList instance(){
	    if (showListInstance == null){
	        return (showListInstance = new ShowList());
	    } else{
	        return showListInstance;
	    }
    }

    /**
     * Search for a specific show based on it's show name.
     * 
     * @param showName name of the show used to search for.
     * @return show object if one is found. Otherwise, null
     */
    public Show search(String showName){
    	return super.search(showName); 
    }

    /**
     * Inserts the given show into the show list.
     * 
     * @param show The show object that will be saved in show list.
     * @return true if show has been added to the list, false otherwise. 
     */
    public boolean insertShow(Show show){
        return super.insert(show);
    }

    /**
     * Removes a show with the given showName from the showList.
     * 
     * @param showName name of the show to be removed
     * @return true if a show with the showName has been removed, false otherwise.
     */
    public boolean removeShow(String showName){
    	return super.remove(showName);
    }

    /**
     * Gets an iterator for the ShowList.
     * @return an iterator of the Show List.
     */
    public Iterator<Show> getShowList(){
        return super.iterator();
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    /*
     * This method is used to return a particular show from the Showlist with the showDate.
     * If the showDate passed is within range of one of the shows in the list, this
     * show object is returned, else it returns null.
     * This function differs from generic search function since searching based on date and not
     * title of show
   
     * function will be used by theater function you will define next:
     * 		isShowDateAvaiable( date showDate, int ticketNum): bool
     * 		returns true if seat capacity for show's particular date > ticket number
     * 				else returns false
     * function will also be used by Sell Ticket Use Cases to get and use particular show object
     * */
    public Show getShowFromShowDate(Calendar showDate) {
    	Iterator<Show> showIterator = this.getShowList();
    	while (showIterator.hasNext()) {
        	Show show = (Show) showIterator.next();
        	if (isDateWithinRange(showDate,show.getStartDate(),show.getEndDate())) {
				return show; 
			}
        }
    	//loop through all shows in list without show that plays on that date, so return null
    	return null;
    }
    
    /*isDateWithinRange is an aggregate function to be used specifically by getShowFromShowDate
     * to determine if the calendar object passed in is within the particular show object's start
     * and EndDate range. 
     * Warning! - Function uses Calendar equals function to determine if date is on the Start
	 * or EndDate specified. This wont work in cases when we have extra info besides 
	 * MM/DD/YYYY, since the seconds,minutes, etc. may be different!*/
    public boolean isDateWithinRange(Calendar date, Calendar rangeStartDate, Calendar rangeEndDate) {
		/*************** verbose ***********************/
		/*System.out.println("\n\nDateOfInterest : "
				+ date.getTime());
		System.out.println("rangeStartDate : "
				+ rangeStartDate.getTime());
		System.out.println("rangeEndDate : "
				+ rangeEndDate.getTime());
		//System.out.println("dateToValidate : " + dateToValidate);*/
		/************************************************/
		
		/* Check if date is between the start and end dates given*/
		/* Note 1 - below we check if date is in range of the two calendar dates, not
		 * their times. Use the getTime() method if using Date objects.*/
		if (date.after(rangeStartDate)
				&& date.before(rangeEndDate) 
				|| date.equals(rangeStartDate)
				|| date.equals(rangeEndDate)) {
		
			//ok everything is fine, date in range
			return true;
		} else {
			return false;
		}
	}

} // End of class ShowList

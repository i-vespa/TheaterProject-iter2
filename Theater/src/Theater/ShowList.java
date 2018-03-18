package Theater;

import java.io.Serializable;
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

} // End of class ShowList

package Theater;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



/** 
 * ShowList class is a singleton class that holds the list of shows that
 * are inside the system. Methods such as inserting, searching,
 * and deleting shows from the list.
 *
 * @author Franklin Ortega
 * Date: Feb 23, 2018
 */
public class ShowList implements Serializable {
    private static final long serialVersionUID = 1L;
    private static ShowList showListInstance;
    private List showList = new LinkedList<>();

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
        for(Iterator iterator = showList.iterator(); iterator.hasNext();){
            Show show = (Show) iterator.next();
            if (show.getName().equals(showName)) {
                return show;
            }
        }
        return null; 
    }

    /**
     * Inserts the given show into the show list.
     * 
     * @param show The show object that will be saved in show list.
     * @return true if show has been added to the list, false otherwise. 
     */
    public boolean insertShow(Show show){
        return showList.add(show);
    }

    /**
     * Removes a show with the given showName from the showList.
     * 
     * @param showName name of the show to be removed
     * @return true if a show with the showName has been removed, false otherwise.
     */
    public boolean removeShow(String showName){
        for (Iterator iterator = showList.iterator(); iterator.hasNext();){
            Show show = (Show) iterator.next();
            if (show.getName().equals(showName)){
                // we found the customer with the specified client ID, remove them from list
                showList.remove(show);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets an iterator for the ShowList.
     * @return an iterator of the Show List.
     */
    public Iterator getShowList(){
        return showList.iterator();
    }
    
    @Override
    public String toString() {
        return showList.toString();
    }

} // End of class ShowList

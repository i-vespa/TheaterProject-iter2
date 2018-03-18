package Theater;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Generic List serves as a generic list manager. It allows the programmer to create
 * lists of any data type and utilize the methods below such as search, insert, remove
 * and iterator for iterating over the contents of said list. For sub-classes to utilize this
 * class, they should extend it's functionality.
 * 
 * The type of object that utilizes the generic list must implement the interface Matchable.
 * @author David Jaqua
 *
 * @param <T> the type of object that is being stored in the list (ex: Client)
 * @param <K> the type of object that is used to identify matchability (ex: String)
 */
public class GenericList<T extends Matchable<K>, K> implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<T> list = new LinkedList<T>();
	
	/**
	 * Searches for an element of the generic list given a specific key
	 * @param key the key for which to identify the element
	 * @return the element if one is found, otherwise, null if no element with
	 * 			the key was found
	 */
	public T search(K key) {
		for(Iterator<T> iterator = list.iterator(); iterator.hasNext();){
            T element = iterator.next();
            if (element.matches(key)) {
                return element;
            }
        }
        return null; 
	}
	
	/**
	 * Inserts an element into the generic list
	 * @param element the element to insert
	 * @return true if the element was able to be inserted, otherwise, false
	 */
	public boolean insert(T element) {
		return list.add(element);
	}
	
	/**
	 * Removes an element from the generic list with the specified key
	 * @param key the key from which to identify an element in the list
	 * @return true if the element was found and removed, otherwise, false
	 */
	public boolean remove(K key) {
		T element = search(key);
		if (element != null) {
			return list.remove(element);
		} else {
			return false;
		}
	}
	
	/**
	 * Returns an iterator for the generic list
	 * @return iterator for the list
	 */
	public Iterator<T> iterator(){
		return list.iterator();
	}
	
	/**
	 * Returns the toString equivalent of the generic list
	 */
	@Override
	public String toString() {
		return list.toString();
	}
}

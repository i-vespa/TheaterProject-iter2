package Theater;

/**
 * Matchable interface serves as a requirement of classes such that they
 * implement the matches method. This enables us to create a generic list
 * which all list classes implement. By doing this, we can then offer 
 * equality checking of objects in these lists and identify an object we
 * are looking for. Take for example the search method: in order to implement
 * this class we must be able to verify whether two objects are the same based
 * on some key.
 * 
 * @author David Jaqua
 *
 * @param <K> Some type of key
 */
public interface Matchable<K> {

	/**
	 * Checks whether the item's key matches the given key
	 * @param key the key of another item
	 * @return true if both keys match, otherwise, false
	 */
	public boolean matches(K key);
}

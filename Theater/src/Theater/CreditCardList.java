package Theater;

import java.io.Serializable;
import java.util.Iterator;

/**
* Credit card list represents a list of credit cards applied to a single customer.
* This class contains methods for searching, inserting, and removing credit cards from the list
*
*/
public class CreditCardList extends GenericList<CreditCard, String> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	* Creates a new credit card list and adds the customers' first card.
	*/
	public CreditCardList (CreditCard card) {
		super.insert(card);
	}
	
	/**
	 * Search for a specific Credit Card in the list based on the credit card number.
	 * @param creditCardNum the credit card number to remove
	 * @return creditCard credit card found in the list, otherwise, null if no card was found
	 */
	public CreditCard search(String creditCardNum){
		return super.search(creditCardNum);
	}
    
	/**
	* Removes Credit Card from list, based on credit card number given.
	* Returns boolean true if card found and removed, otherwise it returns false
	* @param creditCardNum the credit card number of the card to remove
	* @return true if card was found and removed, otherwise false.
	*/
	public boolean removeCreditCard(String creditCardNum){
		return super.remove(creditCardNum);
	}
    
	/**
	* Inserts credit card in to the credit card list
	* @param creditCard the credit card to insert
	* @return true if inserted successfully, else false
	*/
	public boolean insertCreditCard(CreditCard creditCard){
		return super.insert(creditCard);
	}
    
	/**
	* Gets an iterator for the credit card list
	* @return Iterator of the credit card list
	*/
	public Iterator<CreditCard> getCreditCardList(){
		return super.iterator();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}

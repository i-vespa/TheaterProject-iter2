package Theater;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
* Credit card list represents a list of credit cards applied to a single customer.
* This class contains methods for searching, inserting, and deleting credit cards from the list
*
*/
public class CreditCardList implements Serializable{
	private static final long serialVersionUID = 1L;
	private List creditCardList = new LinkedList();
	
	/**
	* Creates a new credit card list and adds the customers' first card.
	*/
	public CreditCardList (CreditCard card) {
		creditCardList.add(card);
	}
	
	/**
	 * Search for a specific Credit Card in the list based on the credit card number.
	 * @param creditCardNum the credit card number to remove
	 * @return creditCard credit card found in the list, otherwise, null if no card was found
	 */
	public CreditCard search(String creditCardNum){
        	for(Iterator iterator = creditCardList.iterator(); iterator.hasNext();){
			CreditCard creditCard = (CreditCard) iterator.next();
			if (creditCard.getCardNum().equals(creditCardNum)) {
				return creditCard;
			    }
		}
		return null; // card not found.
	}
    
	/**
	* Removes Credit Card from list, based on credit card number given.
	* Returns boolean true if card found and removed, otherwise it returns false
	* @param creditCardNum the credit card number of the card to remove
	* @return true if card was found and removed, otherwise false.
	*/
	public boolean removeCreditCard(String creditCardNum){
		for (Iterator iterator = creditCardList.iterator(); iterator.hasNext();){
			CreditCard creditCard = (CreditCard) iterator.next();
			if (creditCard.getCardNum().equals(creditCardNum)){
				// found the card with the specified card number, remove it from list
				creditCardList.remove(creditCard);
				return true;
			}
		}
		return false;
	}
    
	/**
	* Inserts credit card in to the credit card list
	* @param creditCard the credit card to insert
	* @return true if inserted successfully, else false
	*/
	public boolean insertCreditCard(CreditCard creditCard){
		return creditCardList.add(creditCard);
	}
    
	/**
	* Gets an iterator for the credit card list
	* @return Iterator of the credit card list
	*/
	public Iterator getCreditCardList(){
		return creditCardList.iterator();
	}

	@Override
	public String toString() {
		return creditCardList.toString();
	}
}

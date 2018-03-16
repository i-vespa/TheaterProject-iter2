
package Theater;
import java.io.Serializable;

/**
 * CreditCard class holds the credit card details for a client; such as the card
 * owners' customer ID, the card number, and the card expiry date. Includes methods
 * of getting these fields and setting them.
 *
 */
public class CreditCard implements Serializable {
	private static final long serialVersionUID = 1L;
	private String customerID;
	private String cardNum ;
	private String expDate;
	
	public CreditCard(String customerID, String cardNum, String expDate) {
		super();
		this.customerID = customerID;
		this.cardNum = cardNum;
		this.expDate = expDate;
	}
	
	/**
	 * Gets the customer id who owns the card.
	 * @return customerID string
	 */
	public String getCustomerID() {
		return customerID;
	}
	
	/**
	 * Gets the card number
	 * @return cardNum string
	 */
	public String getCardNum() {
		return cardNum;
	}
	
	/**
	 * Gets the cards' expiry date
	 * @return expDate string
	 */
	public String getExpDate() {
		return expDate;
	}
	
	/**
	 * Sets the customer ID of the customer who owns the card
	 * @param customerID customerID
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	/**
	 * Sets the card number with the specified string
	 * @param cardNum new card number
	 */
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	/**
	 * Sets the expriy date for the card.
	 * @params expDate the new expiry date
	 */
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
	@Override
	public String toString() {
		return "CreditCard [customerID=" + customerID + ", cardNum=" + cardNum + ", expDate=" + expDate + "]";
	}
}

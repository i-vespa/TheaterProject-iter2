package Theater;

/**
* Author: Vanessa
* Customer Class composes a single Customer object; with name, address, phone number, and a credit card, as attributes.
* Note that it doesn't include id attribute
* This is because it is composed via getId(), function from CustomerIdServer class
*
*/

import java.util.*;
import java.io.Serializable;
public final class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String phoneNum;
    private String customerId;
    private CreditCardList creditCardList;
    private static final String CUSTOMER_STRING = "Cus";

    /**
    * Constructor instantiates Customer
    * @param name name of the member
    * @param address address of the member
    * @param phone phone number of the member
    * @param cardNumber customer credit card number
    * @param expirationDate credit card expiration date
    */
    public  Customer(String name, String address, String phone,
                        String cardNumber, String expirationDate) {
        this.name = name;
        this.address = address;
        this.phoneNum = phone;
        customerId = CUSTOMER_STRING + (CustomerIdServer.instance()).getId();
        CreditCard creditCard = new CreditCard(customerId, cardNumber, expirationDate);
        
        // Create a new credit card list for this customer which their only credit card
        creditCardList = new CreditCardList(creditCard);
    }
    
    /** 
    * getCreditCardList gets a collection of the Credit Card List 
    * @return an Iterator of the Credit Card List
    */
    public Iterator getCreditCardList() {
    		return creditCardList.getCreditCardList();
    }
    
    /**
    * addCreditCard() adds a credit card to the collection.
    * @param creditCard an object of the Credit Card class
    * @return true if the credit card has been added to the Credit Card collection, false otherwise.
    */
    public boolean addCreditCard(CreditCard creditCard){
        return creditCardList.insertCreditCard(creditCard);
    }
    
    /**
    * removeCreditCard() removes an especified credit card from the collection.
    * @param creditCardNumber a string of the credit card number.
    * @return true if the credit card has been removed from the Credit Card collection, false otherwise.
    */
    public boolean removeCreditCard(String creditCardNumber) {
    	return creditCardList.removeCreditCard(creditCardNumber);
    }
    
    /**
    * searchCreditCard() searchs for an especified crredit card in the collection
    * @param creditCardNumber a string of the credit card number.
    * @return true if the credit card has been found in the Credit Card collection, false otherwise.
    */
    public CreditCard searchCreditCard(String creditCardNumber) {
    	return creditCardList.search(creditCardNumber);
    }
    
    /**
    * getNumberOfCards() gets a count of the existing Credit Cards in the Credit Card collection
    * @return count number of the existing Credit Cards
    */
    public int getNumberOfCards(){
    	Iterator iterator = creditCardList.getCreditCardList();
    	int count = 0;
    	while(iterator.hasNext()) {
    		iterator.next();
    		count++;
    	}
        return count;
    }

    
    /**
    * getName() gets the name of the costumer who owns the credit card
    * @return name of the costumer
    */
    public String getName() {
    	return name;
    }
    
    /**
    * getAddress() gets the name of the customer owner of the credit card
    * @return address of the customer
    */
    public String getAddress() {
    	return address;
    }

    /**
    * getPhoneNum() gets the phone number of customer owner of the credit card
    * @return phobneNum of the customer
    */
    public String getPhoneNum() {	
    	return phoneNum;
    }

    /**
    * getCustomerId() gets the Customer id (Note: customer id is created in the CustomerIdServer class for uniqueness).
    * @return customerId customer id.
    */
    public String getCustomerId() {
    	return customerId;
    }
    
    /**
     * getCustomerCreditCard() Gets the first credit card this customer has
     * @return Customer's first credit card
     */
    public CreditCard getCustomerCreditCard(){
        return (CreditCard) creditCardList.getCreditCardList().next();
    }

    /**
    * setName() sets the name of the customer
    * @param name customer's name
    */
    public void setName(String name) {
            this.name = name;  
    }

    /**
    * setAddress() sets customer address
    * @param address customer address
    */
    public void setAddress(String address) {
            this.address = address;
    }

    /**
    * setPhoneNum() sets phone number of the customer
    * @param phoneNum customer phone number
    */
    public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Customer[" + "name=" + name + ", address=" + address + 
                ", phoneNum=" + phoneNum +", customerId=" + customerId +
                ", creditCardList=" + creditCardList.toString() + "]";
    }
}

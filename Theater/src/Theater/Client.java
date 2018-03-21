package Theater;

import java.io.Serializable;

/**
 * Client class represents a client who has attributes such as a name, address
 * phone number, client ID, and balance.
 * @author David Jaqua
 */
public class Client implements Matchable<String>, Serializable {

	private static final long serialVersionUID = 1L;
	private String name, address, phoneNum, clientID;
	private static final String CLIENT_STRING = "Cli";
	private double balance;

	/**
	 * Creates a new client object with the given info and set's their balance to 0
	 * @param name Name of Client 
	 * @param address Client address 
	 * @param phoneNum Client phone number
	 */
	public Client(String name, String address, String phoneNum){
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		balance = 0; // Initial balance 0, teacher requirement.

		// fetch a new client id
		clientID = CLIENT_STRING + ClientIDServer.instance().getID();
    }

	/**
	 * Gets the clients' name
	 * @return clients' name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Gets the clients' address
	 * @return clients' address
	 */
	public String getAddress(){
		return address;
	}

	/**
	 * Gets the clients' phone number
	 * @return clients' phone number
	 */
	public String getPhoneNum(){
		return phoneNum;
	}

	/**
	 * Gets the clients' ID
	 * @return clients' ID
	 */
	public String getClientID(){
		return clientID;
	}

	/**
	 * Gets the clients' balance
	 * @return clients' balance
	 */
	public double getBalance(){
		return balance;
	}

	/**
	 * Sets the clients' name
	 * @param name clients' new name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Sets the clients' address
	 * @param address clients' new address
	 */
	public void setAddress(String address){
		this.address = address;
	}

	/**
	 * Sets the clients' phone number
	 * @param phoneNum clients' new phone number
	 */
	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
 	}

	/**
	 * Sets the clients' balance
	 * @param balance clients' new balance
	 */
	public void setBalace(double balance){
		this.balance = balance;
	}
    
	/**
	 * Adds the specified balance to the clients' current balance
	 * @param balance the balance to add
	 */
	public void updateClientBalance(double balance) {
		this.balance += balance;
	}
	
	/**
	 * Pays out the client the specified amount. Essentially deducts balance from the
	 * clients' current balance. The clerk would then payout the customer this amount
	 * in cash or check.
	 * @param payOut the amount to payout to the client
	 * @return true if the funds were payed, otherwise false if they have insufficient funds
	 */
	public boolean payClient(double payOut) {
		if (balance < payOut) {
			return false; // cannot payout client more than their balance
		} else {
			balance -= payOut; // deduct payout amount from balance
			return true;
		}
	}
	
	/**
	 * Checks if this client is the same as another client with the given
	 * client id
	 * @param id the id of another client
	 * @return true if the clients are the same, otherwise false
	 */
	@Override
	public boolean matches(String id) {
		return clientID.equals(id) ? true : false;
	}
	
	@Override
	public String toString(){
		return "Client [name=" + name + " address=" + address + " phoneNum=" + phoneNum 
				+ " clientID=" + clientID + " balance=" + balance + "]";
	}
}

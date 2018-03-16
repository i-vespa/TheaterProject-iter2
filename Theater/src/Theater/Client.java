package Theater;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Client class represents a client who has attributes such as a name, address
 * phone number, client ID, and balance.
 * @author David Jaqua
 */
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name, address, phoneNum, clientID;
	private static final String CLIENT_STRING = "Cli";
	private int balance;

	/**
	 * 
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
	public int getBalance(){
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
	public void setBalace(int balance){
		this.balance = balance;
	}
    
	@Override
	public String toString(){
		return "Client [name=" + name + " address=" + address + " phoneNum=" + phoneNum 
				+ " clientID=" + clientID + " balance=" + balance + "]";
	}
}

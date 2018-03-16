package Theater;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** 
 * CustomerList class contains a list of customers in the system. Contains methods
 * for inserting, searching and removing a customer from the list.
 *
 * @author Franklin Ortega
 * Date: Feb 20, 2018
 */
public class CustomerList implements Serializable {
    private static final long serialVersionUID = 1L;
    private static CustomerList customerListInstance;
    private List customerList = new LinkedList<>();

    private CustomerList() {
    }

	/**
	 * Implements the singleton class of CustomerList.
	 * 
	 * @return new instance of singleton class if doesn't exists, otherwise
	 *          returns an existing one.
	 */
	public static CustomerList instance(){
		if (customerListInstance == null){
			return (customerListInstance = new CustomerList());
		} else{
			return customerListInstance;
		}
	}

    /**
     * Search for a specific customer based in a customer id.
     * 
     * @param customerID id String used to search customer in the list.
     * @return customer customer object if one was found in the list, otherwise null.
     */
	public Customer search(String customerID){
		for(Iterator iterator = customerList.iterator(); iterator.hasNext();){
			Customer customer = (Customer) iterator.next();
			if (customer.getCustomerId().equals(customerID)) {
				return customer;
			}
		}
		return null; // customer not found.
	}

	/**
	 * Adds customer to Customer List.
	 * 
	 * @param customer a new customer to add to the list.
	 * @return true if customer has been added to the list, false otherwise. 
	 */
	public boolean insertCustomer(Customer customer){
		return customerList.add(customer);
	}

	/**
	 * Removes a customer from the CustomerList, it uses customer id for 
	 * identifying customer on the list.
	 * 
	 * @param customerID the customer to be removed.
	 * @return true if customer has been removed, false otherwise.
	 */
	public boolean removeCustomer(String customerID){
		for (Iterator iterator = customerList.iterator(); iterator.hasNext();){
			Customer customer = (Customer) iterator.next();
			if (customer.getCustomerId().equals(customerID)){
				// we found the customer with the specified client ID, remove them from list
				customerList.remove(customer);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets and iterator for the customer list.
	 * @return an iterator of the CustomerList.
	 */
	public Iterator getCustomers(){
		return customerList.iterator();
	}

    @Override
    public String toString() {
        return customerList.toString();
    }
} // End of class CustomerList

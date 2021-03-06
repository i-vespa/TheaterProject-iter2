package Theater;

import java.io.Serializable;
import java.util.Iterator;

/**
 * CustomerList class contains a list of customers in the system. Contains methods
 * for inserting, searching and removing a customer from the list.
 *
 * @author Franklin Ortega
 * Date: Feb 20, 2018
 */
public class CustomerList extends GenericList<Customer, String> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static CustomerList customerListInstance;

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
		return super.search(customerID);
	}

	/**
	 * Adds customer to Customer List.
	 * 
	 * @param customer a new customer to add to the list.
	 * @return true if customer has been added to the list, false otherwise. 
	 */
	public boolean insertCustomer(Customer customer){
		return super.insert(customer);
	}

	/**
	 * Removes a customer from the CustomerList, it uses customer id for 
	 * identifying customer on the list.
	 * 
	 * @param customerID the customer to be removed.
	 * @return true if customer has been removed, false otherwise.
	 */
	public boolean removeCustomer(String customerID){
		return super.remove(customerID);
	}

	/**
	 * Gets and iterator for the customer list.
	 * @return an iterator of the CustomerList.
	 */
	public Iterator<Customer> getCustomers(){
		return super.iterator();
	}

    @Override
    public String toString() {
        return super.toString();
    }
} // End of class CustomerList

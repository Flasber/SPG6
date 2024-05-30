package model;

import java.util.HashMap;
import java.util.Map;

public class CustomerContainer {

	private static CustomerContainer instance;
	private Map<String, Customer> customers;

	private CustomerContainer() {
		customers = new HashMap<>();
	}

	public static CustomerContainer getInstance() {
		if (instance == null) {
			instance = new CustomerContainer();
		}
		return instance;
	}

	public void addCustomer(Customer customer) {
		customers.put(customer.getTlf(), customer);
	}

	public Customer findCustomer(String tlf) {
		return customers.get(tlf);
	}
}

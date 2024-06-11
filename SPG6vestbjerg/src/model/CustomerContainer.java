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

	public boolean emailExists(String email){
		boolean emailFound = false;
		for (Customer c : customers.values()){
			if (c instanceof PrivateCustomer && ((PrivateCustomer) c).getEmail().contains(email)){
				emailFound = true;
			}
		}
		return emailFound;
	}

	public boolean cvrExists(String cvr){
		boolean cvrFound = false;
		for (Customer c : customers.values()){
			if (c instanceof BusinessCustomer && ((BusinessCustomer) c).getCvr().contains(cvr)){
				cvrFound = true;
			}
		}
		return cvrFound;
	}

	public boolean eanExists(String ean){
		boolean eanFound = false;
		for (Customer c : customers.values()){
			if (c instanceof BusinessCustomer && ((BusinessCustomer) c).getEan().contains(ean)){
				eanFound = true;
			}
		}
		return eanFound;
	}
}

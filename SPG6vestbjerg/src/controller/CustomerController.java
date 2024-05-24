package controller;

import model.Customer;
import model.CustomerContainer;

public class CustomerController {

	public Customer findCustomer(String tlf) {
		CustomerContainer customerContainer = CustomerContainer.getInstance();

		return customerContainer.findCustomer(tlf);

	}

}

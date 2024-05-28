package model;

public class PrivateCustomer extends Customer {

	private String email;
	private int customerNumber;

	public PrivateCustomer(String name, String tlf, String email, int customerNumber) {
		super(name, tlf);
		this.email = email;
		this.customerNumber = customerNumber;
	}

	public String getEmail() {
		return email;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

}

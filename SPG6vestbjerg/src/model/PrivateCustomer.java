package model;

public class PrivateCustomer extends Customer {

	private String email;

	public PrivateCustomer(String name, String tlf, String email) {
		super(name, tlf);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}
}

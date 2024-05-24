package model;

public class Business extends Customer {

	private String cvr;
	private String ean;

	public Business(String customerId, String name, String tlf, String cvr, String ean) {
		super(customerId, name, tlf);

		this.cvr = cvr;
		this.ean = ean;

	}

	public String getCvr() {
		return cvr;
	}

	public String getEan() {
		return ean;
	}

}

package model;

public class BusinessCustomer extends Customer {

	private String cvr;
	private String ean;

	public BusinessCustomer(String name, String tlf, String cvr, String ean) {
		super(name, tlf);
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

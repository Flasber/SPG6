package model;

public class WarrantyDescription {
	private String name;

	public WarrantyDescription(String name) {
		super();
		this.name = name;
	}

	public Warranty createWarranty(WarrantyCopy copy) {
		return new Warranty(this, copy);
	}

	public String getName() {
		return this.name;
	}
}

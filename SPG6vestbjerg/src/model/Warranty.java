package model;

public class Warranty {

	private WarrantyDescription description;

	private WarrantyCopy copy;

	public Warranty(WarrantyDescription warranty, WarrantyCopy copy) {
		this.description = warranty;
		this.copy = copy;
	}

	public WarrantyCopy getCopy() {
		return copy;
	}

	public WarrantyDescription getDescription() {
		return description;
	}

}

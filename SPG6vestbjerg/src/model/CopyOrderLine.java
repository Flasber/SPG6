package model;

public class CopyOrderLine extends OrderLine {

	private WarrantyProduct.Copy copy;

	public WarrantyProduct.Copy getCopy() {
		return copy;
	}

	public CopyOrderLine(WarrantyProduct.Copy copy) {
		super();
		this.copy = copy;
	}

}

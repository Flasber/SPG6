package model;

public class WarrantyBillableLine extends BillableLine {

	private WarrantyProduct.Copy copy;

	public WarrantyProduct.Copy getCopy() {
		return copy;
	}

	public WarrantyBillableLine(WarrantyProduct.Copy copy) {
		super();
		this.copy = copy;
	}

}

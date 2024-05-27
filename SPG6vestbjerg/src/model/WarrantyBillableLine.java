package model;


import java.math.BigDecimal;

public class WarrantyBillableLine extends BillableLine {


	private WarrantyProduct.Copy copy;

	public WarrantyProduct.Copy getCopy() {
		return copy;
	}

	public WarrantyBillableLine(WarrantyProduct.Copy copy) {
		super();
		this.copy = copy;
	}

	@Override
	protected BigDecimal getSubTotal() {
		return copy.getProduct().getPrice().getPrice();

	}

}

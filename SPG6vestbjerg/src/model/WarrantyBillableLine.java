package model;

import java.math.BigDecimal;

public class WarrantyBillableLine extends BillableLine {

	private WarrantyProduct.Copy copy;

	public WarrantyBillableLine(WarrantyProduct.Copy copy) {
		super();
		this.copy = copy;
	}

	@Override
	public BigDecimal getSubTotal() {
		return copy.getPrice().getPrice();
	}

	@Override
	public int getQuantity() {
		return 1;
	}

	@Override
	protected WarrantyProduct.Copy getItem() {
		return copy;
	}
}

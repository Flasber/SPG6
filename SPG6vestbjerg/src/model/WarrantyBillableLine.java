package model;

import java.math.BigDecimal;

public class WarrantyBillableLine extends BillableLine {

	private WarrantyProduct.Copy copy;

	public WarrantyBillableLine(WarrantyProduct.Copy copy) {
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
	public WarrantyProduct.Copy getItem() {
		return copy;
	}
}

package model;

import java.math.BigDecimal;

public class NormalBillableLine extends BillableLine {

	int quantity;
	NonWarrantyProduct product;

	public NonWarrantyProduct getProduct() {
		return product;
	}

	public NormalBillableLine(int quantity, NonWarrantyProduct product) {
		super();
		this.quantity = quantity;
		this.product = product;
	}

	public void increaseQuantity(int qu) {
		quantity = quantity + qu;

	}

	@Override
	public BigDecimal getSubTotal() {
		BigDecimal pris = product.getPrice().getPrice();
		BigDecimal qu = new BigDecimal(quantity);
		return pris.multiply(qu);

	}

	@Override
	public NonWarrantyProduct getItem() {
		return product;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

}

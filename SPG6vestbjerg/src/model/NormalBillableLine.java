package model;

import java.math.BigDecimal;

public class NormalBillableLine extends BillableLine {

	int quantity;
	Product product;

	public Product getProduct() {
		return product;
	}

	public NormalBillableLine(int quantity, Product product) {
		super();
		this.quantity = quantity;
		this.product = product;
	}

	public void increaseQuantity(int qu) {
		quantity = quantity + qu;

	}

	@Override
	protected BigDecimal getSubTotal() {
		BigDecimal pris = product.getPrice().getPrice();
		BigDecimal qu = new BigDecimal(quantity);
		return pris.multiply(qu);

	}
}

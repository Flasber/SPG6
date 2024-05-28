package model;

public class CompositeLine {
	NonWarrantyProduct product;
	private int quantity;

	public NonWarrantyProduct getProduct() {
		return product;
	}

	public CompositeLine(int quantity, NonWarrantyProduct product) {
		super();
		this.quantity = quantity;
		this.product = product;
	}

	protected NonWarrantyProduct getItem() {
		return product;
	}

	protected int getQuantity() {
		return quantity;
	}

	public void increaseQuantity(int qu) {
		quantity = quantity + qu;
	}
}
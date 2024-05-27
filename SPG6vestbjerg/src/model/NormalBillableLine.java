package model;

public class NormalBillableLine extends OrderLine {
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
}

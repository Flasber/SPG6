package model;

public class ProductOrderLine extends OrderLine {
	int quantity;
	Product product;

	public Product getProduct() {
		return product;
	}

	public ProductOrderLine(int quantity, Product product) {
		super();
		this.quantity = quantity;
		this.product = product;
	}

	public void increaseQuantity(int qu) {
		quantity = quantity + qu;

	}
}

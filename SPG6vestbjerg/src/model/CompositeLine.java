package model;

public class CompositeLine {
	private NonWarrantyProduct product;
	private int quantity;

	public CompositeLine(int quantity, NonWarrantyProduct product) {
		this.quantity = quantity;
		this.product = product;
	}

	public NonWarrantyProduct getItem() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void increaseQuantity(int qu) {
		quantity = quantity + qu;
	}

	public NonWarrantyProduct getProduct() {
		return product;
	}

	@Override
	public String toString() {
		return "Navn: " + product.getName() + ". ||          Antal brugt i samleprodukt: " + quantity;

	}
}
package model;

public class Stock {

	private int quantity;

	public Stock(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void lowerquantity(int amount) {
		if (quantity > amount) {
			quantity = quantity - amount;
			if (quantity < amount) {
				quantity = 0;
			}
		}

	}

}

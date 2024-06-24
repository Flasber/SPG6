package model;

public class CompositeDescriptionLine {

	private int quantity;
	private ProductDescription component;

	public CompositeDescriptionLine(ProductDescription component, int quantity) {
		super();
		this.quantity = quantity;
		this.component = component;
	}

	public ProductDescription getComponent() {
		return component;
	}

	public int getQuantity() {
		return quantity;
	}

}

package model;

public abstract class NonWarrantyProduct extends Product {

	public NonWarrantyProduct(String description, String name, Price price, String sku, String barcode) {
		super(description, name, price, sku, barcode);
		// TODO Auto-generated constructor stub
	}

	public abstract void addStockLocation(int quantity);
}

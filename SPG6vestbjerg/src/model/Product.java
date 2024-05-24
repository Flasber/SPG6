package model;

import java.math.BigDecimal;

public class Product {
	private String description;
	private String name;
	private String sku;
	private String barcode;
	private BigDecimal price;

	public Product(String description, String name, String sku, String barcode) {
		super();
		this.description = description;
		this.name = name;
		this.sku = sku;
		this.barcode = barcode;
	}

	public String getBarcode() {
		return barcode;
	}

}

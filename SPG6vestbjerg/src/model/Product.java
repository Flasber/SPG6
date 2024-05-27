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

	public String getName() {
		return name;
	}

	public String getSku() {
		return sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}

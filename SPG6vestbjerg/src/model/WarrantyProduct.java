package model;

import java.util.ArrayList;
import java.util.List;

public class WarrantyProduct extends Product {

	private List<Copy> copies;

	public WarrantyProduct(String description, String name, String sku, String barcode) {
		super(description, name, sku, barcode);
		copies = new ArrayList<>();
	}

	public List<Copy> getCopies() {
		return copies;
	}

}

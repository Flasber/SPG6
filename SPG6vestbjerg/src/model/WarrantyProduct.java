package model;

import java.util.ArrayList;
import java.util.List;

public class WarrantyProduct extends Product {
	private String warranty;
	private List<Copy> copies;
	private Copy currentCopy;

	public WarrantyProduct(String description, String name, String sku, String barcode, String warranty) {
		super(description, name, sku, barcode);
		this.warranty = warranty;
		copies = new ArrayList<>();

	}

	public boolean addCopy(Copy c) {
		boolean isAdded = false;
		if (c != null) {
			copies.add(c);
			isAdded = true;
		}
		return isAdded;
	}

	public String getWarranty() {
		return warranty;
	}

	public Copy findCopyByCopyId(int cId) {

		boolean searching = true;
		for (int i = 0; i < copies.size() && searching; i++) {
			Copy c = copies.get(i);
			if (c.getCopyId() == cId) {
				currentCopy = c;
				searching = false;
			}
		}
		return currentCopy;
	}

}
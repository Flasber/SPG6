package model;

import java.util.ArrayList;
import java.util.List;

public class WarrantyProduct extends Product {
	private String warranty;
	private List<Copy> copies;
	private Copy currentCopy;

	public class Copy {
		private int copyId;
		private String warranty;
		private int timesReturned;
		private WarrantyProduct product;
		private boolean isSold;

		private Copy(WarrantyProduct product, int copyId, String warranty, int timesReturned) {
			super();
			this.product = product; // assc. part 1
			this.copyId = copyId;
			this.warranty = warranty;
			this.timesReturned = timesReturned;
		}

		public boolean isForSale() {
			return isSold;
		}

		public void setIsSold(boolean state) {
			this.isSold = state;
		}

		public int getCopyId() {
			return copyId;
		}
	}

	public Copy createCopy(int copyId, String warranty, int timesReturned) {
		Copy copy = new Copy(this, copyId, warranty, timesReturned);
		copies.add(copy); // assc. part2
		return copy;
	}

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
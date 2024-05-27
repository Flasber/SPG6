package model;

import java.util.ArrayList;
import java.util.List;

public class WarrantyProduct extends Product {

	private List<Copy> copies;

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
	}

	public Copy createCopy(int copyId, String warranty, int timesReturned) {
		Copy copy = new Copy(this, copyId, warranty, timesReturned);
		copies.add(copy); // assc. part2
		return copy;
	}

	public WarrantyProduct(String description, String name, String sku, String barcode) {
		super(description, name, sku, barcode);
		copies = new ArrayList<>();
	}

	public List<Copy> getCopies() {
		return copies;
	}

}

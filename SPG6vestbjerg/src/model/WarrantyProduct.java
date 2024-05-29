package model;

import java.util.ArrayList;
import java.util.List;

public class WarrantyProduct extends Product {
	private String warranty;
	private List<Copy> copies;

	public class Copy implements BillableItem {
		private int copyId;
		private String warranty;
		private int timesReturned;
		private WarrantyProduct product;
		private boolean isSold;

		private Copy(WarrantyProduct product, int copyId, String warranty, int timesReturned) {
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

		public WarrantyProduct getProduct() {
			return product;
		}

		public Price getPrice() {
			return product.getPrice();
		}

		public String getName() {
			return product.getName();
		}

		public String getWarranty() {
			return warranty;
		}

		@Override
		public String getBarcode() {

			return "" + copyId;
		}

		@Override
		public void removestock(int a) {
			// TODO Auto-generated method stub
			System.err.println("not yet done");
		}
	}

	public Copy createCopy(int copyId, String warranty, int timesReturned) {
		Copy copy = new Copy(this, copyId, warranty, timesReturned);
		copies.add(copy); // assc. part2
		return copy;
	}

	public WarrantyProduct(String description, String name, Price price, String sku, String barcode, String warranty) {
		super(description, name, price, sku, barcode);
		this.warranty = warranty;
		copies = new ArrayList<>();

	}

	private boolean addCopy(Copy c) {
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
		Copy foundCopy = null;
		boolean searching = true;
		for (int i = 0; i < copies.size() && searching; i++) {
			Copy c = copies.get(i);
			if (c.getCopyId() == cId) {
				foundCopy = c;
				searching = false;
			}
		}
		return foundCopy;
	}

	@Override
	public Price getPrice() {
		return super.getPrice();
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void removestock(int a) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception();
	}

}
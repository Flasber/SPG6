package model;

import java.util.ArrayList;
import java.util.List;

public class WarrantyProduct extends Product {
	private String warranty;
	private List<Copy> copies;
	private Copy currentCopy;

	public class Copy implements BillableItem {
		private int copyId;
		private String warranty;
		private int timesReturned;
		private WarrantyProduct product;
		private boolean isSold;

		private Copy(WarrantyProduct product, int copyId, String warranty, int timesReturned) {
			this.product = product;
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

		public void setProduct(WarrantyProduct p) {
			product = p;
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

		public void setCopyId(int copyId) {
			this.copyId = copyId;
		}

		public void setWarranty(String warranty) {
			this.warranty = warranty;
		}

		public void setTimesReturned(int timesReturned) {
			this.timesReturned = timesReturned;
		}

		@Override
		public String toString() {
			return "Kopinummer: " + copyId + ". Garantikode: " + warranty;
		}
	}

	public WarrantyProduct(String description, String name, Price price, String sku, String barcode, String warranty) {
		super(description, name, price, sku, barcode);
		this.warranty = warranty;
		copies = new ArrayList<>();

	}

	public Copy createCopy(int copyId, String warranty, int timesReturned) {
		Copy copy = new Copy(this, copyId, warranty, timesReturned);
		copies.add(copy); // assc. part2
		return copy;
	}

	private boolean addCopy(Copy c) {
		boolean isAdded = false;
		if (c != null) {
			copies.add(c);
			isAdded = true;
		}
		return isAdded;
	}

	public void removeCopy(Copy c) {
		if (c != null) {
			copies.remove(c);
			c.setProduct(null);
		}
	}

	public String getWarranty() {
		return warranty;
	}

	public List<Copy> getCopies() {
		return copies;
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
		throw new Exception();
	}

	@Override
	public int getQuantity() {
		return copies.size();
	}

}
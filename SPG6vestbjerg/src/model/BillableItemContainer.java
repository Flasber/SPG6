package model;

import java.util.ArrayList;
import java.util.List;

public class BillableItemContainer {

	private static BillableItemContainer instance;
	private List<Product> products;

	private BillableItemContainer() {
		products = new ArrayList<>();
	}

	public static BillableItemContainer getInstance() {
		if (instance == null) {
			instance = new BillableItemContainer();
		}
		return instance;
	}

	public boolean addProduct(Object p) {
		boolean addProduct = false;
		if (p != null && p instanceof Product) {
			products.add((Product) p);
			addProduct = true;
		}
		return addProduct;
	}

	/*
	 * public Copy findCopy(String barcode, int cId) { Copy kopi = null; Product
	 * pr=null; boolean searching = true; for (int i = 0; i < products.size() &&
	 * searching; i++) {
	 * 
	 * pr = products.get(i); if (pr instanceof WarrantyProduct){ WarrantyProduct
	 * p=(WarrantyProduct)pr; if(p.getBarcode().equals(barcode)) { Copy c =
	 * p.findCopyByCopyId(cId); if (c != null) { kopi = c; searching = false;
	 * 
	 * } } } }
	 * 
	 * 
	 * return kopi;
	 * 
	 * }
	 */

	public WarrantyProduct.Copy findCopy(String barcode) {
		WarrantyProduct.Copy kopi = null;
		Product product = null;
		boolean searching = true;
		if (barcode.length() == 8) {
			String bar = barcode.substring(0, 5);
			String code = barcode.substring(5);
			int copyId = Integer.parseInt(code);

			for (int i = 0; i < products.size() && searching; i++) {

				product = products.get(i);
				if (product instanceof WarrantyProduct p && p.getBarcode().equals(bar)) {
					WarrantyProduct.Copy c = p.findCopyByCopyId(copyId);
					if (c != null) {
						kopi = c;
						searching = false;
					}
				}
			}
		}

		return kopi;

	}

	public BillableItem findProduct(String barcode) {
		Product p = null;
		boolean searching = true;
		if (barcode.length() == 5) {
			for (int i = 0; i < products.size() && searching; i++) {
				Product pr = products.get(i);
				if (pr.getBarcode().equals(barcode)) {
					p = pr;
					searching = false;
				}
			}
		}
		return p;
	}

}

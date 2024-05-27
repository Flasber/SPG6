package model;

import java.util.ArrayList;
import java.util.List;

public class ProductContainer {

	private static ProductContainer instance;
	private List<Product> products;

	private ProductContainer() {
		products = new ArrayList<>();
	}

	public static ProductContainer getInstance() {
		if (instance == null) {
			instance = new ProductContainer();
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

	public Copy findCopy(String barcode) {
		Copy kopi = null;
		Product pr = null;
		boolean searching = true;
		if (barcode.length() == 8) {
			String bar = barcode.substring(0, 5);
			String code = barcode.substring(5);
			int cId = Integer.parseInt(code);

			for (int i = 0; i < products.size() && searching; i++) {

				pr = products.get(i);
				if (pr instanceof WarrantyProduct) {
					WarrantyProduct p = (WarrantyProduct) pr;
					if (p.getBarcode().equals(bar)) {
						Copy c = p.findCopyByCopyId(cId);
						if (c != null) {
							kopi = c;
							searching = false;

						}
					}
				}
			}
		}

		return kopi;

	}

	public Product findProduct(String barcode) {
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

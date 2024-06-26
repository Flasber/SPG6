package model;

import java.util.ArrayList;
import java.util.List;

public class BillableItemContainer {

	private static BillableItemContainer instance;
	private List<Product> products;// det er ikke en BillableItemContainer,det er ProduktContainer

	private BillableItemContainer() {
		products = new ArrayList<>();
	}

	public static BillableItemContainer getInstance() {
		if (instance == null) {
			instance = new BillableItemContainer();
		}
		return instance;
	}

	public boolean addProduct(Product p) {
		boolean addProduct = false;
		if (p != null) {
			products.add(p);
			addProduct = true;
		}
		return addProduct;
	}

	public BillableItem findCopy(String barcode) {
		WarrantyProduct.Copy copy = null;
		Product product = null;
		boolean searching = true;

		if (barcode.length() != 8) {
			return null;
		}

		String productCodePart = barcode.substring(0, 5);
		String copyCodePart = barcode.substring(5);
		int copyId = Integer.parseInt(copyCodePart);

		for (int i = 0; i < products.size() && searching; i++) {
			product = products.get(i);
			if (product instanceof WarrantyProduct p && p.getBarcode().equals(productCodePart)) {
				WarrantyProduct.Copy c = p.findCopyByCopyId(copyId);
				if (c != null) {
					copy = c;
					searching = false;
				}
			}
		}

		return copy;

	}

	public BillableItem findProduct(String barcode) {
		Product result = null;
		if (barcode.length() == 5) {
			for (int i = 0; i < products.size() && result == null; i++) {
				Product pr = products.get(i);
				if (pr.getBarcode().equals(barcode)) {
					result = pr;
				}
			}
		}
		return result;
	}

}

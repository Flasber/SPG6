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

	public void removeProduct(Product p) {
		products.remove(p);
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

	public Product findProduct(String barcode) {
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

	public boolean barcodeExists(String barcodeToCheck, Product p) {
		for (Product product : products) {
			String barcode = product.getBarcode();
			if (barcode != null && barcodeToCheck != null && barcode.equals(barcodeToCheck) && product != p) {
				return true;
			}
		}
		return false;
	}

	public boolean skuExists(String sku, Product p) {
		boolean skuFound = false;
		for (Product product : products) {
			if (product.getSku().equals(sku) && product != p) {
				skuFound = true;
			}
		}
		return skuFound;
	}

	public boolean copyWarrantyExists(String warranty) {
		boolean copyWarrantyFound = false;
		for (Product p : products) {
			if (p instanceof WarrantyProduct) {
				for (WarrantyProduct.Copy c : ((WarrantyProduct) p).getCopies()) {
					if (c.getWarranty().equals(warranty)) {
						copyWarrantyFound = true;
					}
				}
			}
		}
		return copyWarrantyFound;
	}

	public List<Product> getAllProducts() {
		return new ArrayList<Product>(products);
	}

	public void addCompositeLineToProduct(CompositeProduct p, String addedProductBarcode, int quantity) {
		if (p instanceof CompositeProduct) {
			if (findProduct(addedProductBarcode) instanceof NonWarrantyProduct) {
				NonWarrantyProduct apb = (NonWarrantyProduct) findProduct(addedProductBarcode);
				p.addCompositeLine(apb, quantity);
			}

		}
	}

	public void removeCompositeLineFromProduct(CompositeProduct cp, CompositeLine cl) {
		cp.removeCompositeLine(cl);
	}
}

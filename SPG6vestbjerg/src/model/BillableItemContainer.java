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

	public void removeProduct(Product p){
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

	public boolean barcodeExists(String barcode){
		boolean barcodeFound = false;
		for (Product p : products){
			if (p.getBarcode().equals(barcode)){
				barcodeFound = true;
			}
		}
		return barcodeFound;
	}

	public boolean skuExists(String sku){
		boolean skuFound = false;
		for (Product p : products){
			if (p.getSku().equals(sku)){
				skuFound = true;
			}
		}
		return skuFound;
	}

	public boolean copyWarrantyExists(String warranty){
		boolean copyWarrantyFound = false;
		for (Product p : products){
			if (p instanceof WarrantyProduct){
				for (WarrantyProduct.Copy c : ((WarrantyProduct) p).getCopies()){
					if (c.getWarranty().equals(warranty)){
						copyWarrantyFound = true;
					}
				}
			}
		}
		return copyWarrantyFound;
	}
}

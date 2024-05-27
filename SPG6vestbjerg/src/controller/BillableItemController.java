package controller;

import model.Product;
import model.ProductContainer;
import model.WarrantyProduct;

public class BillableItemController {
	public WarrantyProduct.Copy findCopy(String barcode) {
		WarrantyProduct.Copy c = null;

		ProductContainer pc = ProductContainer.getInstance();

		c = pc.findCopy(barcode);
		return c;
	}

	public Product findProduct(String barcode) {
		Product p = null;

		ProductContainer pc = ProductContainer.getInstance();
		p = pc.findProduct(barcode);

		return p;
	}

}
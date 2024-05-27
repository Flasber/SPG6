package controller;

import model.Copy;
import model.Product;
import model.ProductContainer;

public class ProductController {
	public Copy findCopy(String barcode) {
		Copy c = null;

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
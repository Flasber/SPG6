package controller;

import java.util.List;

import exceptionHandling.BarcodeAlreadyExistsException;
import exceptionHandling.SkuAlreadyExistsException;
import exceptionHandling.WarrantyInUseException;
import model.BasicProduct;
import model.BillableItem;
import model.BillableItemContainer;
import model.CompositeLine;
import model.CompositeProduct;
import model.NonWarrantyProduct;
import model.Price;
import model.Product;
import model.WarrantyProduct;
import model.WarrantyProduct.Copy;

public class BillableItemController {

	public BillableItem findItem(String barcode) {
		BillableItemContainer container = BillableItemContainer.getInstance();

		BillableItem bi = null;
		BillableItem copy = container.findCopy(barcode);

		if (copy != null) {
			bi = copy;
		} else {
			bi = container.findProduct(barcode);
		}
		return bi;
	}

	public void removeStock(BillableItem item, int quantity) throws Exception {
		item.removestock(quantity);
	}

	public void addStock(NonWarrantyProduct item, int quantity) {
		item.addStockLocation(quantity);
	}

	public void addCopy(WarrantyProduct wp, int copyId, String warranty)
			throws BarcodeAlreadyExistsException, WarrantyInUseException {
		BillableItemContainer container = BillableItemContainer.getInstance();
		if (wp.findCopyByCopyId(copyId) != null) {
			throw new BarcodeAlreadyExistsException("Barcode is already in use");
		}
		if (container.copyWarrantyExists(warranty)) {
			throw new WarrantyInUseException("Warranty is already in use");
		}
		wp.createCopy(copyId, warranty, 0);
	}

	public Product createProduct(String description, String name, Price price, String sku, String barcode,
			String warranty, boolean isComposite) throws BarcodeAlreadyExistsException, SkuAlreadyExistsException {
		BillableItemContainer container = BillableItemContainer.getInstance();
		Product p = null;
		if (container.barcodeExists(barcode, p)) {
			throw new BarcodeAlreadyExistsException("barcode is already in use");
		}
		if (container.skuExists(sku, p)) {
			throw new SkuAlreadyExistsException("SKU is already in use");
		} else if (warranty == null && !isComposite) {
			p = new BasicProduct(description, name, price, sku, barcode);
		} else if (warranty == null && isComposite) {
			p = new CompositeProduct(description, name, price, sku, barcode);
		} else if (warranty != null) {
			p = new WarrantyProduct(description, name, price, sku, barcode, warranty);
		}
		if (p != null) {
			container.addProduct(p);
		}
		return p;
	}

	public void deleteProduct(Product p) {
		BillableItemContainer container = BillableItemContainer.getInstance();
		if (p instanceof WarrantyProduct) {
			List<Copy> copies = ((WarrantyProduct) p).getCopies();
			for (Copy c : copies) {
				((WarrantyProduct) p).removeCopy(c);
			}
		}
		container.removeProduct(p);
	}

	public void updateProduct(Product p, String description, String name, Price price, String sku, String barcode)
			throws BarcodeAlreadyExistsException, SkuAlreadyExistsException {
		BillableItemContainer container = BillableItemContainer.getInstance();
		if (container.barcodeExists(barcode, p)) {
			throw new BarcodeAlreadyExistsException("Barcode is already in use");
		}
		if (container.skuExists(sku, p)) {
			throw new SkuAlreadyExistsException("SKU is already in use");
		}
		p.setDescription(description);
		p.setName(name);
		p.addPrice(price);
		p.setSku(sku);
		p.setBarcode(barcode);
	}

	public void updateCopy(Copy c, int copyId, String warranty, int timesReturned)
			throws BarcodeAlreadyExistsException, WarrantyInUseException {
		BillableItemContainer container = BillableItemContainer.getInstance();
		WarrantyProduct p = c.getProduct();
		if (p.findCopyByCopyId(copyId) != null) {
			throw new BarcodeAlreadyExistsException("Barcode is already in use");
		}
		if (container.copyWarrantyExists(warranty)) {
			throw new WarrantyInUseException("Warranty is already in use");
		}
		c.setCopyId(copyId);
		c.setWarranty(warranty);
		c.setTimesReturned(timesReturned);
	}

	public List<Product> getAllProducts() {
		BillableItemContainer container = BillableItemContainer.getInstance();
		return container.getAllProducts();
	}

	public void deleteCopy(WarrantyProduct wp, WarrantyProduct.Copy c) {
		wp.removeCopy(c);
	}

	public void addCompositeLineToProduct(CompositeProduct p, String addedProductBarcode, int quantity) {
		BillableItemContainer container = BillableItemContainer.getInstance();
		container.addCompositeLineToProduct(p, addedProductBarcode, quantity);
	}

	public void removeCompositeLineFromProduct(CompositeProduct p, CompositeLine cl) {
		BillableItemContainer container = BillableItemContainer.getInstance();
		container.removeCompositeLineFromProduct(p, cl);
	}

}
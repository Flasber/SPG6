package controller;

import java.util.List;

import exceptionHandling.*;
import model.BasicProduct;
import model.BillableItem;
import model.BillableItemContainer;
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

	public void addStock(NonWarrantyProduct item, int quantity){
		item.addStockLocation(quantity);
	}

	public void addCopy(WarrantyProduct wp, int copyId, String warranty){
		wp.createCopy(copyId, warranty, 0);
	}

	public Product createProduct(String description, String name, Price price, String sku, String barcode, String warranty, boolean isComposite) throws BarcodeAlreadyExistsException{
		Product p = null;
		if (findItem(barcode) != null){
			throw new BarcodeAlreadyExistsException("barcode is already in use");
		}
			else if (warranty == null && !isComposite){
				p = new BasicProduct(description, name, price, sku, barcode);
			}
			else if (warranty == null && isComposite){
				p = new CompositeProduct(description, name, price, sku, barcode);
			}
			else if (warranty != null){
				p = new WarrantyProduct(description, name, price, sku, barcode, warranty);

			}
		if (p != null){
			BillableItemContainer container = BillableItemContainer.getInstance();
			container.addProduct(p);
		}
		return p;
	}

	public void deleteProduct(Product p){
		BillableItemContainer container = BillableItemContainer.getInstance();
		if (p instanceof WarrantyProduct ){
			List<Copy> copies = ((WarrantyProduct) p).getCopies();
			for (Copy c : copies){
				((WarrantyProduct) p).removeCopy(c);
			}
		}
		container.removeProduct(p);
	}

	public void updateProduct(Product p, String description, String name, Price price, String sku, String barcode) throws BarcodeAlreadyExistsException, SkuAlreadyExistsException{
		if (p.getBarcode() == barcode){
			throw new BarcodeAlreadyExistsException("Barcode is already in use");
		}
		if (p.getSku() == sku){
			throw new SkuAlreadyExistsException("SKU is already in use");
		}
		p.setDescription(description);
		p.setName(name);
		p.addPrice(price);
		p.setSku(sku);
		p.setBarcode(barcode);
	}

	public void updateCopy(Copy c, int copyId, String warranty, int timesReturned) throws BarcodeAlreadyExistsException, WarrantyInUseException{
		WarrantyProduct p = c.getProduct();
		if (p.findCopyByCopyId(copyId) != null){
			throw new BarcodeAlreadyExistsException("Barcode is already in use");
		}
		if (c.getWarranty() == warranty){
			throw new WarrantyInUseException("Warranty is already in use");
		}
		c.setCopyId(copyId);
		c.setWarranty(warranty);
		c.setTimesReturned(timesReturned);
	}

}
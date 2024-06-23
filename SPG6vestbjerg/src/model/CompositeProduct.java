package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeProduct extends NonWarrantyProduct {
	private List<CompositeLine> compositeLines;

	public CompositeProduct(String description, String name, Price price, String sku, String barcode) {
		super(description, name, price, sku, barcode);
		compositeLines = new ArrayList<>();
	}

	public void addCompositeLine(NonWarrantyProduct p, int qu) {
		if (p == this) {
			throw new IllegalArgumentException("A composite product cannot contain itself.");
		}
		Iterator<CompositeLine> it = compositeLines.iterator();
		boolean isFound = false;
		CompositeLine result = null;
		while (it.hasNext() && !isFound) {
			CompositeLine nextline = it.next();

			NonWarrantyProduct product = nextline.getProduct();
			if (product == p) {
				isFound = true;
				result = nextline;

			}

		}
		if (isFound) {
			result.increaseQuantity(qu);
		} else {
			compositeLines.add(new CompositeLine(qu, p));
		}
	}

	@Override
	public void removeStock(int a) throws Exception {
		for (int i = 0; i < compositeLines.size(); i++) {
			CompositeLine c = compositeLines.get(i);
			BillableItem p = c.getItem();
			int amount = c.getQuantity();
			p.removeStock(amount);
		}
	}

	@Override
	public int getQuantity() {
		int lowestQuantityBasicProduct = Integer.MAX_VALUE;
		for (int i = 0; i < compositeLines.size(); i++) {
			CompositeLine c = compositeLines.get(i);
			Product p = c.getItem();
			int ratioOfStockToNeeds = p.getQuantity() / c.getQuantity();
			if (ratioOfStockToNeeds < lowestQuantityBasicProduct) {
				lowestQuantityBasicProduct = ratioOfStockToNeeds;
			}
		}
		return lowestQuantityBasicProduct;
	}

	@Override
	public void addStockLocation(int quantity) {
		for (int i = 0; i < compositeLines.size(); i++) {
			CompositeLine c = compositeLines.get(i);
			NonWarrantyProduct p = c.getItem();
			p.addStockLocation(c.getQuantity() * quantity);
		}
	}

	public List<CompositeLine> getCompositeLines() {
		return compositeLines;
	}

	public void removeCompositeLine(CompositeLine cl) {
		compositeLines.remove(cl);
	}

}

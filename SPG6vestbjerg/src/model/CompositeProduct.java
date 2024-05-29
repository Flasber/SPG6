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
	public void removestock(int a) throws Exception {
		for (int i = 0; i < compositeLines.size(); i++) {
			CompositeLine c = compositeLines.get(i);
			Product p = c.getItem();
			int amount = c.getQuantity();
			p.removestock(amount);
		}
	}
}

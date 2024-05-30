package controller;

import model.BillableItem;
import model.BillableItemContainer;

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

}
package controller;

import model.BillableItem;
import model.BillableItemContainer;

public class BillableItemController {

	public BillableItem findItem(String barcode) {
		BillableItem bi = null;

		BillableItemContainer bic = BillableItemContainer.getInstance();

		BillableItem copy = bic.findCopy(barcode);

		if (copy != null) {
			bi = copy;
		} else {
			bi = bic.findProduct(barcode);
		}
		return bi;
	}

}
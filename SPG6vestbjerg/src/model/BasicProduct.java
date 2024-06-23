package model;

import java.util.ArrayList;
import java.util.List;

public class BasicProduct extends NonWarrantyProduct {

	private List<Stock> stockLocations;

	public BasicProduct(String description, String name, Price price, String sku, String barcode) {
		super(description, name, price, sku, barcode);
		stockLocations = new ArrayList<>();
	}

	@Override
	public void addStockLocation(int q) {
		if (stockLocations.isEmpty()) {
			stockLocations.add(new Stock(q));
		} else {
			int currentStock = stockLocations.get(0).getQuantity();
			stockLocations.add(0, new Stock(q + currentStock));
		}

	}

	@Override
	public void removeStock(int a) {
		Stock s = null;
		s = stockLocations.get(0);
		s.lowerQuantity(a);
	}

	@Override
	public int getQuantity() {
		int q = 0;
		for (Stock s : stockLocations) {
			q = q + s.getQuantity();
		}
		return q;
	}
}
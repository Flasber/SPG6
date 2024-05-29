package model;

import java.util.ArrayList;
import java.util.List;

public class BasicProduct extends NonWarrantyProduct {

	// how do you remove from the right stock
	private List<Stock> StockLocations;

	public BasicProduct(String description, String name, Price price, String sku, String barcode) {
		super(description, name, price, sku, barcode);
		StockLocations = new ArrayList<>();
	}

	public void addstocklocation(int q) {
		StockLocations.add(new Stock(q));

	}

	@Override
	public void removestock(int a) {
		Stock s = null;
		s = StockLocations.get(0);
		s.lowerquantity(a);
	}
}
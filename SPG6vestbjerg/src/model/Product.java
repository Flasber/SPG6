package model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class Product implements BillableItem {
	private String description;
	private String name;
	private String sku;
	private String barcode;
	private Map<LocalDateTime, Price> prices;

	public Product(String description, String name, String sku, String barcode) {
		this.description = description;
		this.name = name;
		this.sku = sku;
		if (barcode.length() == 5) {
			this.barcode = barcode;
		}
		prices = new HashMap<>();

	}

	public void addPrice(Price pr) {
		prices.put(pr.getStartTime(), pr);
	}

	public String getBarcode() {
		return barcode;
	}

	public Price getPrice() {
		return getPriceForDate(LocalDateTime.now());
	}

	public Price getPriceForDate(LocalDateTime date) {
		Collection<Price> allPrices = prices.values();
		Iterator<Price> iterator = allPrices.iterator();
		while (iterator.hasNext()) {
			Price price = iterator.next();
			if (price.isInTheRange(date)) {
				return price;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

}
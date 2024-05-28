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

	public Product(String description, String name, Price price, String sku, String barcode) {
		this.description = description;
		this.name = name;
		this.sku = sku;
		if (barcode.length() == 5) {
			this.barcode = barcode;
		}
		prices = new HashMap<>();
		addPrice(price);
	}

	public void addPrice(Price pr) {
		prices.put(pr.getStartTime(), pr);
	}

	public String getBarcode() {
		return barcode;
	}

	public Price getPrice() {
		return getPrice(LocalDateTime.now());
	}

	public Price getPrice(LocalDateTime dateTime) {
		return getPriceForDate(dateTime);
	}

	public Price getPriceForDate(LocalDateTime date) {
		Collection<Price> allPrices = prices.values();
		Iterator<Price> iterator = allPrices.iterator();

		// All dateTimes are after this
		Price latestPriceSoFar = null;
		LocalDateTime latestDateTimeSoFar = LocalDateTime.MIN;

		// Visit each pair of price and startTime
		while (iterator.hasNext()) {
			Price price = iterator.next();
			LocalDateTime priceStartTime = price.getStartTime();

			// If price's startTime is in the future, it's not valid (yet)
			boolean isInTheFuture = priceStartTime.isAfter(date);
			boolean isLatestSoFar = priceStartTime.isAfter(latestDateTimeSoFar);

			if (!isInTheFuture && isLatestSoFar) {
				// This price is the latest so far
				latestPriceSoFar = price;
				latestDateTimeSoFar = priceStartTime;
			}
		}

		// Every pair has been checked, so latestPriceSoFar is THE latest price (or
		// null).
		return latestPriceSoFar;
	}

	public String getName() {
		return name;
	}

}
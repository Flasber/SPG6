package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {

	private BigDecimal price;
	private LocalDateTime startTime;

	public Price(Number price) {
		this.price = new BigDecimal(price.doubleValue());
		this.startTime = LocalDateTime.now();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

}
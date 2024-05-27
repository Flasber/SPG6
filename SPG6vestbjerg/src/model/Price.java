package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {

	private BigDecimal price;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public BigDecimal getPrice() {
		return price;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return startTime;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

}

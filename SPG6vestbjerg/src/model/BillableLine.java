package model;

import java.math.BigDecimal;

public abstract class BillableLine {

	public abstract BigDecimal getSubTotal();

	public String toString() {
		return String.format(" %dx %s", getQuantity(), getItem().getName());
	}

	public abstract BillableItem getItem();

	public abstract int getQuantity();

}

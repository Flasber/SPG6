package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface Billable {
	public LocalDateTime getCreateDate();

	public int getBillableId();

	public List<BillableLine> getBillableLines();

	public BigDecimal getTotal();
}

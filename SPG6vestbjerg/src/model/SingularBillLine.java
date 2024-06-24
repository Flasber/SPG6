package model;

public class SingularBillLine implements BillLine {
	private Billable billable;
	private int quantity;

	public SingularBillLine(Billable billable, int quantity) {
		super();
		this.billable = billable;
		this.quantity = quantity;
	}

	public Billable getBillable() {
		return billable;
	}

	@Override
	public ProductDescription getDescription() {
		return billable.getDescription();
	}

	@Override
	public int getQuantity() {
		return quantity;
	}
}

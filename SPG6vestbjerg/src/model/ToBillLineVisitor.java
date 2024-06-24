package model;

public class ToBillLineVisitor {
	private int quantity;

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BillLine visitBillable(Billable billable) {
		return new SingularBillLine(billable, quantity);
	}

	public BillLine visitBillableDescription(BillableDescription description) {
		return visitBillable(description.toBillable());
	}

	public BillLine visitCompositeDescription(CompositeProductDescription description) {
		return new CompositeBillLine(description, quantity);
	}

}
package model;

public class Rental implements Billable {

	private RentalDescription description;
	private RentalCopy copy;

	public Rental(RentalDescription description, RentalCopy copy) {
		this.description = description;
		this.copy = copy;

	}

	@Override
	public BillLine accept(ToBillLineVisitor visitor) {
		return visitor.visitBillable(this);
	}

	public RentalCopy getCopy() {
		return copy;
	}

	@Override
	public RentalDescription getDescription() {
		return description;
	}

}

package model;

public interface BillableDescription extends ProductDescription, AddableToBill {
	@Override
	public BillLine accept(ToBillLineVisitor visitor);

	public Billable toBillable();

}

package model;

public interface Billable extends AddableToBill {
	@Override
	public BillLine accept(ToBillLineVisitor visitor);

	public ProductDescription getDescription();
}

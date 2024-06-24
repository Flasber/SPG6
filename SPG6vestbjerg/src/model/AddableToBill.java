package model;

public interface AddableToBill {
	public BillLine accept(ToBillLineVisitor visitor);
}

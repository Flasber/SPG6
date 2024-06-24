package model;

public class FungibleProductDescription implements ProductDescription, BillableDescription, Billable {

	private String name;

	public FungibleProductDescription(String name) {
		super();
		this.name = name;
	}

	@Override
	public BillLine accept(ToBillLineVisitor visitor) {
		return visitor.visitBillable(this);
	}

	@Override
	public ProductDescription getDescription() {
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Billable toBillable() {
		return this;
	}

}

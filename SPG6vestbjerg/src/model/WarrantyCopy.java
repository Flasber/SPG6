package model;

public class WarrantyCopy implements Billable {

	private Warranty warranty;
	private WarrantyProductDescription description;

	public WarrantyCopy(WarrantyProductDescription description) {
		this.description = description;
	}

	@Override
	public BillLine accept(ToBillLineVisitor visitor) {
		return visitor.visitBillable(this);
	}

	public void attachWarranty(Warranty warranty) {
		this.warranty = warranty;
	}

	@Override
	public WarrantyProductDescription getDescription() {
		return description;
	}

	public Warranty getWarranty() {
		return warranty;
	}

}

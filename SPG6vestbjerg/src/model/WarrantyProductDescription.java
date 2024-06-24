package model;

import java.util.ArrayList;
import java.util.List;

public class WarrantyProductDescription implements BillableDescription, ProductDescription {

	private String name;

	private WarrantyDescription warrantyDescription;
	private List<WarrantyCopy> copies;

	public WarrantyProductDescription(String name, WarrantyDescription warrantyDescription) {
		super();
		this.name = name;

		this.warrantyDescription = warrantyDescription;
		this.copies = new ArrayList<>();
	}

	@Override
	public BillLine accept(ToBillLineVisitor visitor) {
		return visitor.visitBillableDescription(this);
	}

	public WarrantyCopy createItem() {
		var copy = new WarrantyCopy(this);
		copies.add(copy);
		return copy;
	}

	private WarrantyCopy findAvailableCopy() {
		for (var copy : copies) {
			if (copy.getWarranty() == null) {
				return copy;
			}
		}
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Billable toBillable() {
		WarrantyCopy copy = findAvailableCopy();
		if (copy == null) {
			throw new IllegalStateException("no copies are available");
		}
		Warranty warranty = warrantyDescription.createWarranty(copy);
		copy.attachWarranty(warranty);
		return copy;
	}

}

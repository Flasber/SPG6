package model;

import java.util.ArrayList;
import java.util.List;

public class RentalDescription implements BillableDescription {

	private List<Rental> rentals;
	private String name;
	private RentalProductDescription productDescription;

	public RentalDescription(String name, RentalProductDescription productDesc) {
		this.name = name;
		this.productDescription = productDesc;
		this.rentals = new ArrayList<>();
	}

	@Override
	public BillLine accept(ToBillLineVisitor visitor) {
		return visitor.visitBillableDescription(this);
	}

	private Rental createRental(RentalCopy copy) {
		var rental = new Rental(this, copy);
		rentals.add(rental);
		return rental;
	}

	@Override
	public String getName() {
		return name;
	}

	public RentalProductDescription getProductDescription() {
		return productDescription;
	}

	@Override
	public Billable toBillable() {
		RentalCopy copy = productDescription.findAvailableCopy();
		if (copy == null) {
			throw new IllegalStateException("no copies are available");
		}
		return this.createRental(copy);
	}

}

package model;

public class RentalCopy {
	private RentalProductDescription description;
	private Rental rental;

	public RentalCopy(RentalProductDescription description) {
		super();
		this.description = description;
	}

	public void attachRental(Rental rental) {
		this.rental = rental;
	}

	public RentalProductDescription getDescription() {
		return description;
	}

	public Rental getRental() {
		return rental;
	}

}

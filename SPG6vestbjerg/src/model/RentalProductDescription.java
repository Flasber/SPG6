package model;

import java.util.ArrayList;
import java.util.List;

public class RentalProductDescription {

	private String name;

	private List<RentalCopy> copies;

	public RentalProductDescription(String name) {
		super();
		this.name = name;
		this.copies = new ArrayList<>();
	}

	public RentalCopy createItem() {
		var copy = new RentalCopy(this);
		copies.add(copy);
		return copy;
	}

	RentalCopy findAvailableCopy() {
		for (var copy : copies) {
			if (copy.getRental() == null) {
				return copy;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

}

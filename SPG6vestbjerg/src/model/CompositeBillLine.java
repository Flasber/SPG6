package model;

import java.util.ArrayList;
import java.util.List;

public class CompositeBillLine implements BillLine {
	private List<BillLine> lines;
	private CompositeProductDescription description;
	private int quantity;

	public CompositeBillLine(CompositeProductDescription description, int quantity) {
		super();
		this.quantity = quantity;
		this.description = description;
		this.lines = new ArrayList<>();
		for (var compositeLine : description.getLines()) {
			lines.add(fromCompositeLine(compositeLine));
		}
	}

	private BillLine fromCompositeLine(CompositeDescriptionLine compositeLine) {
		BillLine line;
		if (compositeLine.getComponent() instanceof CompositeProductDescription compositeDescription) {
			line = new CompositeBillLine(compositeDescription, compositeLine.getQuantity());
		} else if (compositeLine.getComponent() instanceof BillableDescription billableDescription) {
			line = new SingularBillLine(billableDescription.toBillable(), compositeLine.getQuantity());
		} else {
			throw new IllegalArgumentException("expected a CompositeProductDescription or a BillableDescription");
		}
		return line;
	}

	@Override
	public CompositeProductDescription getDescription() {
		return description;
	}

	public List<BillLine> getLines() {
		return lines;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}
}

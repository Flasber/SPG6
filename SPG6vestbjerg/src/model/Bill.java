package model;

import java.util.ArrayList;
import java.util.List;

public class Bill {

	private List<BillLine> lines;

	public Bill() {
		super();
		this.lines = new ArrayList<>();
	}

	public void add(AddableToBill item, int quantity) {
		switch (item) {
		case Billable b -> new SingularBillLine(b, quantity);
		case BillableDescription d -> new SingularBillLine(d.toBillable(), quantity);
		case CompositeProductDescription c -> new CompositeBillLine(c, quantity);
		default -> throw new IllegalArgumentException("Unexpected value: " + item);
		}
	}

	public void addBillable(Billable billable) {
		addBillable(billable, 1);
	}

	public void addBillable(Billable billable, int quantity) {
		var line = new SingularBillLine(billable, quantity);
		lines.add(line);
	}

	public void addBillableDescription(BillableDescription description) {
		addBillableDescription(description, 1);
	}

	public void addBillableDescription(BillableDescription description, int quantity) {
		addBillable(description.toBillable(), quantity);
	}

	public void addCompositeDescription(CompositeProductDescription description) {
		addCompositeDescription(description, 1);
	}

	public void addCompositeDescription(CompositeProductDescription description, int quantity) {
		CompositeBillLine line = new CompositeBillLine(description, quantity);
		lines.add(line);
	}

	private void formatBillLine(BillLine line, StringBuilder sb, int tabLevel) {
		sb.repeat("  ", tabLevel);
		sb.append(String.format("%d %s", line.getQuantity(), line.getDescription().getName()));
		sb.append("\n");

		if (line instanceof CompositeBillLine compositeLine) {
			for (var componentLine : compositeLine.getLines()) {
				formatBillLine(componentLine, sb, tabLevel + 1);
			}
		}
	}

	public String formatReceipt() {
		var sb = new StringBuilder();
		for (var line : lines) {
			formatBillLine(line, sb, 0);
		}
		return sb.toString();
	}

	public List<BillLine> getLines() {
		return lines;
	}
}

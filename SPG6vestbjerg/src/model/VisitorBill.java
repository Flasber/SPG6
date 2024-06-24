package model;

import java.util.ArrayList;
import java.util.List;

public class VisitorBill {
	private List<BillLine> lines;
	private ToBillLineVisitor toBillLineVisitor;

	public VisitorBill() {
		super();
		this.lines = new ArrayList<>();
		this.toBillLineVisitor = new ToBillLineVisitor();
	}

	public void add(AddableToBill item) {
		add(item, 1);
	}

	public void add(AddableToBill item, int quantity) {
		toBillLineVisitor.setQuantity(quantity);
		var line = item.accept(toBillLineVisitor);
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

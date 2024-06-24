package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompositeProductDescription implements ProductDescription, AddableToBill {
	private String name;

	private List<CompositeDescriptionLine> lines;

	public CompositeProductDescription(String name2, Collection<CompositeDescriptionLine> list) {
		super();

		this.name = name2;
		this.lines = new ArrayList<>(list);
	}

	public CompositeProductDescription(String name, List<ProductDescription> of) {
		this(name, of.stream().map(desc -> new CompositeDescriptionLine(desc, 1)).toList());
	}

	@Override
	public BillLine accept(ToBillLineVisitor visitor) {
		return visitor.visitCompositeDescription(this);
	}

	public List<CompositeDescriptionLine> getLines() {
		return lines;
	}

	@Override
	public String getName() {
		return name;
	}
}

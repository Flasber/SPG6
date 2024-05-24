package model;

public class CopyOrderLine extends OrderLine {

	private Copy copy;

	public Copy getCopy() {
		return copy;
	}

	public CopyOrderLine(Copy copy) {
		super();
		this.copy = copy;
	}

}

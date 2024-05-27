package model;

public class Copy {
	private int copyId;
	private String warranty;
	private int timesReturned;

	public Copy(int copyId, String warranty, int timesReturned) {
		this.copyId = copyId;
		this.warranty = warranty;
		this.timesReturned = timesReturned;
	}

	public int getCopyId() {
		return copyId;
	}

	public String getWarranty() {
		return warranty;
	}
}

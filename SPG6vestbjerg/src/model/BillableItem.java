package model;

public interface BillableItem {

	public Price getPrice();

	public String getName();

	public String getBarcode();

	public void removestock(int a) throws Exception;
}

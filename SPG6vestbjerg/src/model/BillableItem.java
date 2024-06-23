package model;

public interface BillableItem {

	public Price getPrice();

	public String getName();

	public String getBarcode();

	public void removeStock(int a) throws Exception;
}

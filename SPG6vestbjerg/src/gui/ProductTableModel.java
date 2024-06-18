package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Product;

public class ProductTableModel extends AbstractTableModel {

	private List<Product> data;
	private final String[] COL_NAMES = { "Navn", "Antal", "Pris", "SKU", "Stregkode" };

	public ProductTableModel() {
		data = new ArrayList<Product>();
	}

	public ProductTableModel(List<Product> data) {
		if (data != null) {
			this.data = data;
		} else {
			this.data = new ArrayList<Product>();
		}

	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		Product p = data.get(rowIndex);
		String res = "";
		switch (columnIndex) {
		case 0:
			res = p.getName();
			break;
		case 1:
			res = String.valueOf(p.getQuantity());
			break;
		case 2:
			res = String.valueOf(p.getPrice().getPrice());
			break;
		case 3:
			res = p.getSku();
			break;
		case 4:
			res = p.getBarcode();
			break;
		default:
			res = "<UNKNOWN " + columnIndex + ">";

		}
		return res;
	}

	@Override
	public String getColumnName(int column) {
		return COL_NAMES[column];
	}

	public Product getSelectedProduct(int row) {
		return data.get(row);
	}

	public void setData(List<Product> products) {

	}

}

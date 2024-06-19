package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.WarrantyProduct;
import model.WarrantyProduct.Copy;

public class CopyListCellRenderer implements ListCellRenderer<WarrantyProduct.Copy> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Copy> list, Copy value, int index, boolean isSelected,
			boolean cellHasFocus) {
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
		return dlcr.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}

}

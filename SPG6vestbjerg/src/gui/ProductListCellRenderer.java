package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.CompositeLine;

public class ProductListCellRenderer implements ListCellRenderer<CompositeLine> {

	@Override
	public Component getListCellRendererComponent(JList<? extends CompositeLine> list, CompositeLine value, int index,
			boolean isSelected, boolean cellHasFocus) {
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
		return dlcr.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}

}

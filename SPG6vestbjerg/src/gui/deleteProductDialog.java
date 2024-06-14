package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BillableItemController;
import model.BillableItem;
import model.Product;

public class deleteProductDialog extends JDialog {
	private JTextField barcodeField;
	private BillableItemController controller;

	public deleteProductDialog(JFrame parent, BillableItemController controller) {
		super(parent, "Delete Product", true);
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 300, 150);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout());

		JLabel barcodeLabel = new JLabel("Product Barcode:");
		panel.add(barcodeLabel);

		barcodeField = new JTextField(20);
		panel.add(barcodeField);

		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteProduct();
			}
		});
		buttonPanel.add(deleteButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPanel.add(cancelButton);

		pack();
		setLocationRelativeTo(getParent());
	}

	private void deleteProduct() {
		try {
			String barcode = barcodeField.getText();
			BillableItem item = controller.findItem(barcode); // Using the findItem method from the controller

			if (item != null && item instanceof Product) {
				controller.deleteProduct((Product) item);
				JOptionPane.showMessageDialog(this, "Product deleted successfully.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}

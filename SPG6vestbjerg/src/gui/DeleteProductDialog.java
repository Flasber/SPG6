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
import model.Product;

public class DeleteProductDialog extends JDialog {
	private JTextField barcodeField;
	private BillableItemController controller;

	public DeleteProductDialog(JFrame parent, BillableItemController controller) {
		super(parent, "Slet Produkt", true);
		this.controller = controller;
		initialize(null);
	}

	public DeleteProductDialog(JFrame parent, BillableItemController controller, Product p) {
		super(parent, "Slet Produkt", true);
		this.controller = controller;
		initialize(p);
	}

	private void initialize(Product p) {
		setBounds(100, 100, 300, 150);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout());

		JLabel barcodeLabel = new JLabel("Produkt Stregkode:");
		panel.add(barcodeLabel);

		barcodeField = new JTextField(20);
		panel.add(barcodeField);

		if (p != null) {
			barcodeField.setText(p.getBarcode());
		}

		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton deleteButton = new JButton("Slet");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteProduct();
			}
		});
		buttonPanel.add(deleteButton);

		JButton cancelButton = new JButton("Annullér");
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
			Product item = (Product) controller.findItem(barcode); // Using the findItem method from the controller

			if (item != null && item instanceof Product) {
				controller.deleteProduct((Product) item);
				JOptionPane.showMessageDialog(this, "Produkt slettet.", "Succes", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Product ikke fundet.", "Fejl", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}

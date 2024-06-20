package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.BillableItemController;
import controller.ProductType;
import exceptionHandling.BarcodeAlreadyExistsException;
import exceptionHandling.SkuAlreadyExistsException;
import model.BasicProduct;
import model.CompositeProduct;
import model.Price;
import model.Product;
import model.WarrantyProduct;

public class CreateProductDialog extends JDialog {
	private JTextField descriptionField;
	private JTextField nameField;
	private JTextField priceField;
	private JTextField skuField;
	private JTextField barcodeField;
	private JCheckBox basicCheckBox;
	private JCheckBox compositeCheckBox;
	private JCheckBox warrantyCheckBox;
	private JButton createButton;
	private BillableItemController controller;
	private Runnable callback;

	public CreateProductDialog(JFrame frame, BillableItemController controller, Runnable callback) {
		super(frame, "Opret Produkt", true);
		this.controller = controller;
		this.callback = callback;

		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.insets = new Insets(5, 5, 5, 5);

		Font labelFont = new Font("Arial", Font.BOLD, 16);
		Font textFieldFont = new Font("Arial", Font.PLAIN, 16);

		JLabel descriptionLabel = new JLabel("Beskrivelse:");
		descriptionLabel.setFont(labelFont);
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(descriptionLabel, cs);

		descriptionField = new JTextField(20);
		descriptionField.setFont(textFieldFont);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(descriptionField, cs);

		JLabel nameLabel = new JLabel("Navn:");
		nameLabel.setFont(labelFont);
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(nameLabel, cs);

		nameField = new JTextField(20);
		nameField.setFont(textFieldFont);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(nameField, cs);

		JLabel priceLabel = new JLabel("Pris:");
		priceLabel.setFont(labelFont);
		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 1;
		panel.add(priceLabel, cs);

		priceField = new JTextField(20);
		priceField.setFont(textFieldFont);
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 2;
		panel.add(priceField, cs);

		JLabel skuLabel = new JLabel("SKU:");
		skuLabel.setFont(labelFont);
		cs.gridx = 0;
		cs.gridy = 3;
		cs.gridwidth = 1;
		panel.add(skuLabel, cs);

		skuField = new JTextField(20);
		skuField.setFont(textFieldFont);
		cs.gridx = 1;
		cs.gridy = 3;
		cs.gridwidth = 2;
		panel.add(skuField, cs);

		JLabel barcodeLabel = new JLabel("Stregkode:");
		barcodeLabel.setFont(labelFont);
		cs.gridx = 0;
		cs.gridy = 4;
		cs.gridwidth = 1;
		panel.add(barcodeLabel, cs);

		barcodeField = new JTextField(20);
		barcodeField.setFont(textFieldFont);
		cs.gridx = 1;
		cs.gridy = 4;
		cs.gridwidth = 2;
		panel.add(barcodeField, cs);

		basicCheckBox = new JCheckBox("Basalprodukt");
		basicCheckBox.setFont(labelFont);
		cs.gridx = 0;
		cs.gridy = 5;
		cs.gridwidth = 1;
		panel.add(basicCheckBox, cs);

		compositeCheckBox = new JCheckBox("Samleprodukt");
		compositeCheckBox.setFont(labelFont);
		cs.gridx = 1;
		cs.gridy = 5;
		cs.gridwidth = 1;
		panel.add(compositeCheckBox, cs);

		warrantyCheckBox = new JCheckBox("Garantiprodukt");
		warrantyCheckBox.setFont(labelFont);
		cs.gridx = 2;
		cs.gridy = 5;
		cs.gridwidth = 1;
		panel.add(warrantyCheckBox, cs);

		ActionListener checkBoxListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == basicCheckBox) {
					compositeCheckBox.setSelected(false);
					warrantyCheckBox.setSelected(false);
				} else if (e.getSource() == compositeCheckBox) {
					basicCheckBox.setSelected(false);
					warrantyCheckBox.setSelected(false);
				} else if (e.getSource() == warrantyCheckBox) {
					basicCheckBox.setSelected(false);
					compositeCheckBox.setSelected(false);
				}
			}
		};

		basicCheckBox.addActionListener(checkBoxListener);
		compositeCheckBox.addActionListener(checkBoxListener);
		warrantyCheckBox.addActionListener(checkBoxListener);

		createButton = new JButton("Opret");
		createButton.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createProduct();
				} catch (BarcodeAlreadyExistsException eb) {
					JOptionPane.showMessageDialog(CreateProductDialog.this, "Stregkode findes allerede.", "Fejl",
							JOptionPane.ERROR_MESSAGE);
				} catch (SkuAlreadyExistsException es) {
					JOptionPane.showMessageDialog(CreateProductDialog.this, "SKU findes allerede.", "Fejl",
							JOptionPane.ERROR_MESSAGE);
				}
				if (frame instanceof ReadProductsGUI) {
					((ReadProductsGUI) frame).init();
				}
			}
		});

		JButton cancelButton = new JButton("Annuller");
		cancelButton.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(createButton);
		buttonPanel.add(cancelButton);

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

		pack();
		setLocationRelativeTo(null);
	}

	private void createProduct() throws BarcodeAlreadyExistsException, SkuAlreadyExistsException {
		String description = descriptionField.getText();
		String name = nameField.getText();
		String priceStr = priceField.getText();
		String sku = skuField.getText();
		String barcode = barcodeField.getText();
		ProductType productType;

		double priceValue = Double.parseDouble(priceStr);

		if (basicCheckBox.isSelected()) {
			productType = ProductType.BASIC;
		} else if (compositeCheckBox.isSelected()) {
			productType = ProductType.COMPOSITE;
		} else if (warrantyCheckBox.isSelected()) {
			productType = ProductType.WARRANTY;
		} else {
			JOptionPane.showMessageDialog(this, "Vælg venligst en produkttype.", "Fejl", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Product createdProduct = controller.createProduct(description, name, new Price(priceValue), sku, barcode,
				productType);

		if (createdProduct != null) {
			String successMessage;
			if (createdProduct instanceof BasicProduct) {
				successMessage = "Basalprodukt oprettet: ";
			} else if (createdProduct instanceof WarrantyProduct) {
				successMessage = "Garantiprodukt oprettet: ";
			} else if (createdProduct instanceof CompositeProduct) {
				successMessage = "Samleprodukt oprettet: ";
			} else {
				successMessage = "Produkt oprettet: ";
			}

			JOptionPane.showMessageDialog(this, successMessage + createdProduct.toString(), "Succes",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
			if (callback != null) {
				callback.run();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Fejl ved oprettelse af produkt.", "Fejl", JOptionPane.ERROR_MESSAGE);
		}
	}
}

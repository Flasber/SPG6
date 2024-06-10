package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.CreateInStoreSaleController;
import exceptionHandling.AddWarrantyProductException;
import exceptionHandling.UnexpectedClassException;
import model.BillableItem;
import model.BillableLine;
import model.Customer;
import model.InStoreSale;

public class InStoreSaleGUI {
	private CreateInStoreSaleController controller;
	private JFrame frame;

	public InStoreSaleGUI(CreateInStoreSaleController controller) {
		this.controller = controller;
		frame = new JFrame("InStore Sale");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);
		resetUI();
	}

	private void resetUI() {
		frame.getContentPane().removeAll();
		createInStoreSale();
		frame.revalidate();
		frame.repaint();
	}

	private void createInStoreSale() {
		JPanel panel = new JPanel(new GridLayout(3, 2));
		JLabel registerNoLabel = new JLabel("Kassenummer:");
		JTextField registerNoField = new JTextField();
		JLabel employeeIdLabel = new JLabel("Medarbejder Nummer:");
		JTextField employeeIdField = new JTextField();
		JButton nextButton = new JButton("Næste");

		panel.add(registerNoLabel);
		panel.add(registerNoField);
		panel.add(employeeIdLabel);
		panel.add(employeeIdField);
		panel.add(new JLabel());
		panel.add(nextButton);

		frame.add(panel);
		frame.revalidate();
		frame.repaint();

		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int registerNo = Integer.parseInt(registerNoField.getText());
				int employeeId = Integer.parseInt(employeeIdField.getText());
				controller.createInStoreSale(registerNo, employeeId);
				frame.getContentPane().removeAll();
				addItemToSale();
			}
		});
	}

	private void addItemToSale() {
		JPanel panel = new JPanel(new GridLayout(3, 2));
		JLabel barcodeLabel = new JLabel("Indtast stregkode:");
		JTextField barcodeField = new JTextField();
		JLabel quantityLabel = new JLabel("Indtast antal:");
		JTextField quantityField = new JTextField();
		JButton addButton = new JButton("Tilføj");
		JButton finishButton = new JButton("Færdig");

		panel.add(barcodeLabel);
		panel.add(barcodeField);
		panel.add(quantityLabel);
		panel.add(quantityField);
		panel.add(addButton);
		panel.add(finishButton);

		frame.add(panel);
		frame.revalidate();
		frame.repaint();

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String barcode = barcodeField.getText();
					int quantity = Integer.parseInt(quantityField.getText());
					BillableItem addedItem = controller.addItemToSale(barcode, quantity);

					if (addedItem == null) {
						JOptionPane.showMessageDialog(frame, "Produkt med stregkode " + barcode + " blev ikke fundet.",
								"Fejl", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frame,
								"Produkt tilføjet: " + addedItem.getName() + " " + addedItem.getPrice().getPrice(),
								"Succes", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (AddWarrantyProductException ae) {
					JOptionPane.showMessageDialog(frame,
							"Garantiprodukter skal ikke skannes med produktstregkoden. Scan venligst kopistregkoden.",
							"Fejl", JOptionPane.ERROR_MESSAGE);
				} catch (UnexpectedClassException uce) {
					JOptionPane.showMessageDialog(frame, "Fejl ved tilføjelse af produkt: " + uce.getMessage(), "Fejl",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Der opstod en fejl: " + ex.getMessage(), "Fejl",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		finishButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				addCustomerToSale();
			}
		});
	}

	private void addCustomerToSale() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JLabel phoneLabel = new JLabel("Indtast Kundens telefonnummer:");
		JTextField phoneField = new JTextField();
		JButton addButton = new JButton("Tilføj");

		panel.add(phoneLabel);
		panel.add(phoneField);
		panel.add(new JLabel());
		panel.add(addButton);

		frame.add(panel);
		frame.revalidate();
		frame.repaint();

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String phoneNumber = phoneField.getText();
				Customer customer = controller.addCustomerToSale(phoneNumber);

				if (customer != null) {
					JOptionPane.showMessageDialog(frame,
							"Kunden blev fundet: " + customer.getName() + " - " + customer.getTlf(), "Succes",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame,
							"Kunde med telefonnummer " + phoneNumber + " blev ikke fundet.", "Fejl",
							JOptionPane.ERROR_MESSAGE);
				}
				frame.getContentPane().removeAll();
				checkIfPaid();
			}
		});
	}

	private void checkIfPaid() {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		JButton payButton = new JButton("Betal");

		panel.add(new JLabel("Betaling..."));
		panel.add(payButton);

		frame.add(panel);
		frame.revalidate();
		frame.repaint();

		payButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InStoreSale sale = null;
				try {
					sale = controller.isPaid();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame,
							"Kan ikke afslutte salget med garantiprodukt: " + ex.getMessage(), "Fejl",
							JOptionPane.ERROR_MESSAGE);
				}

				if (sale != null) {
					JOptionPane.showMessageDialog(frame, "Betaling bekræftet", "Succes",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "Betaling afvist", "Fejl", JOptionPane.ERROR_MESSAGE);
				}
				frame.getContentPane().removeAll();
				printReceipt();
			}
		});
	}

	private void printReceipt() {
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea receiptArea = new JTextArea();
		receiptArea.setEditable(false);

		InStoreSale sale = controller.getLastSale();
		if (sale != null) {
			StringBuilder receipt = new StringBuilder();
			receipt.append("Kvittering\n");
			receipt.append("================================\n");

			for (BillableLine bl : sale.getBillableLines()) {
				receipt.append(bl.toString()).append(" ").append(bl.getSubTotal()).append(" ,-\n");
			}

			receipt.append("================================\n");
			receipt.append("Totalbeløb: ").append(sale.getTotal()).append(" ,-\n");

			receipt.append("""
					,
					 /(  ___
					|  >:===========`
					 )(
					 ""
					""");

			receiptArea.setText(receipt.toString());
		} else {
			receiptArea.setText("Ingen salg fundet.");
		}

		panel.add(new JScrollPane(receiptArea), BorderLayout.CENTER);

		JButton newSaleButton = new JButton("Nyt salg");
		newSaleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetUI();
			}
		});

		panel.add(newSaleButton, BorderLayout.SOUTH);
		frame.add(panel);
		frame.revalidate();
		frame.repaint();
	}
}

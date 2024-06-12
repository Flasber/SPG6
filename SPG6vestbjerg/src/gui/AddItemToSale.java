package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.CreateInStoreSaleController;
import exceptionHandling.AddWarrantyProductException;
import exceptionHandling.UnexpectedClassException;
import model.BillableItem;

public class AddItemToSale extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField barcodeTextField;
	private JTextField quantityTextField;
	public InStoreSaleGUI GUI;
	private CreateInStoreSaleController controller;

	public AddItemToSale(InStoreSaleGUI gUI, CreateInStoreSaleController controller) throws HeadlessException {
		super();
		GUI = gUI;
		this.controller = controller;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItemToSale frame = new AddItemToSale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddItemToSale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JButton addItemToSaleButton = new JButton(" TILFØJ VARE");
		addItemToSaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String barcode = barcodeTextField.getText();
					int quantity = Integer.parseInt(quantityTextField.getText());
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

		addItemToSaleButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));
		GridBagConstraints gbc_addItemToSale = new GridBagConstraints();
		gbc_addItemToSale.insets = new Insets(0, 0, 0, 5);
		gbc_addItemToSale.gridx = 1;
		gbc_addItemToSale.gridy = 0;
		panel.add(addItemToSaleButton, gbc_addItemToSale);

		JButton endSaleButton = new JButton("AFSLUT SALG");
		endSaleButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			}
		});
		endSaleButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));
		GridBagConstraints gbc_endSaleButton = new GridBagConstraints();
		gbc_endSaleButton.insets = new Insets(0, 0, 0, 5);
		gbc_endSaleButton.gridx = 2;
		gbc_endSaleButton.gridy = 0;
		panel.add(endSaleButton, gbc_endSaleButton);

		JButton logOutButton = new JButton("LOG UD");
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.isLoggedIn = false;
				GUI.resetUI();
			}
		});
		logOutButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));
		GridBagConstraints gbc_logOutButton = new GridBagConstraints();
		gbc_logOutButton.gridx = 3;
		gbc_logOutButton.gridy = 0;
		panel.add(logOutButton, gbc_logOutButton);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel barcodeLabel = new JLabel("Stregkode:");
		barcodeLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		GridBagConstraints gbc_barcodeLabel = new GridBagConstraints();
		gbc_barcodeLabel.anchor = GridBagConstraints.EAST;
		gbc_barcodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_barcodeLabel.gridx = 0;
		gbc_barcodeLabel.gridy = 0;
		panel_1.add(barcodeLabel, gbc_barcodeLabel);

		textField = new JTextField();
		GridBagConstraints barcodeTextField = new GridBagConstraints();

		barcodeTextField.insets = new Insets(0, 0, 5, 0);
		barcodeTextField.fill = GridBagConstraints.HORIZONTAL;
		barcodeTextField.gridx = 1;
		barcodeTextField.gridy = 0;
		panel_1.add(textField, barcodeTextField);
		textField.setColumns(10);

		JLabel quantityLabel = new JLabel("Antal:");
		quantityLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_quantityLabel = new GridBagConstraints();
		gbc_quantityLabel.anchor = GridBagConstraints.EAST;
		gbc_quantityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_quantityLabel.gridx = 0;
		gbc_quantityLabel.gridy = 1;
		panel_1.add(quantityLabel, gbc_quantityLabel);

		textField_1 = new JTextField();
		GridBagConstraints quantityTextField = new GridBagConstraints();

		quantityTextField.insets = new Insets(0, 0, 5, 0);
		quantityTextField.fill = GridBagConstraints.HORIZONTAL;
		quantityTextField.gridx = 1;
		quantityTextField.gridy = 1;
		panel_1.add(textField_1, quantityTextField);
		textField_1.setColumns(10);
	}

}

package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IsCustomerDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public InStoreSaleGUI GUI; // Ensure that InStoreSaleGUI is correctly defined elsewhere in your project.

	// Constructor with GUI parameter
	public IsCustomerDialog(InStoreSaleGUI gui) {
		this(); // Call the default constructor to set up the dialog.
		this.GUI = gui;
		setModal(true); // Make the dialog modal.
	}


	/**
	 * Create the dialog.
	 */
	public IsCustomerDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout());

		JLabel isCustomerLabel = new JLabel("Er kunden medlem?");
		isCustomerLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		contentPanel.add(isCustomerLabel);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton noButton = new JButton("NEJ");
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane();
				GUI.checkIfPaid();
				dispose();
			}
		});
		noButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));
		buttonPane.add(noButton);

		JButton isCustomerButton = new JButton("JA");
		isCustomerButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));
		isCustomerButton.setActionCommand("OK");
		isCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane();
				GUI.addCustomerToSale();
				dispose();

			}
		});
		buttonPane.add(isCustomerButton);
	}
}

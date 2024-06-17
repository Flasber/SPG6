package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AdminVerificationDialog extends JDialog {
	private boolean succeeded;
	private JTextField adminNumberField;

	public AdminVerificationDialog(Frame parent) {
		super(parent, "Admin Login", true);

		JPanel panel = new JPanel(new GridLayout(2, 2));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel adminNumberLabel = new JLabel("Admin Nummer:");
		adminNumberField = new JTextField(15);

		panel.add(adminNumberLabel);
		panel.add(adminNumberField);

		JButton loginButton = new JButton("Login");
		JButton cancelButton = new JButton("Annuller");

		panel.add(loginButton);
		panel.add(cancelButton);

		getContentPane().add(panel, BorderLayout.CENTER);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (authenticate(getAdminNumber())) {
					JOptionPane.showMessageDialog(AdminVerificationDialog.this, "Login succesfuld", "Login",
							JOptionPane.INFORMATION_MESSAGE);
					succeeded = true;
					dispose();
				} else {
					JOptionPane.showMessageDialog(AdminVerificationDialog.this, "Ugyldigt admin nummer", "Login",
							JOptionPane.ERROR_MESSAGE);
					adminNumberField.setText("");
					succeeded = false;
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				succeeded = false;
				dispose();
			}
		});

		pack();
		setLocationRelativeTo(parent);
	}

	public String getAdminNumber() {
		return adminNumberField.getText().trim();
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	private boolean authenticate(String adminNumber) {

		return adminNumber.equals("1") || adminNumber.equals("2");
	}
}


package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ReceiptConfirmation extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private InStoreSaleGUI GUI;

	public ReceiptConfirmation(InStoreSaleGUI gUI) {
		super();
		if (gUI == null) {
			throw new IllegalArgumentException();
		}
		this.GUI = gUI;

		setBounds(100, 100, 350, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel receiptLabel = new JLabel("Ønskes kvittering printet?");
				receiptLabel.setHorizontalAlignment(SwingConstants.CENTER);
				receiptLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
				panel.add(receiptLabel);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[] { 45, 63, 0, 0, 0, 0, 0, 0, 0 };
			gbl_buttonPane.rowHeights = new int[] { 21, 0 };
			gbl_buttonPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			buttonPane.setLayout(gbl_buttonPane);
			{
			}
			{
				JButton yesButton = new JButton("JA");
				yesButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GUI.printReceipt();
						dispose();

					}
				});
				yesButton.setFont(new Font("Arial Narrow", Font.BOLD, 10));
				yesButton.setActionCommand("OK");
				GridBagConstraints gbc_yesButton = new GridBagConstraints();
				gbc_yesButton.anchor = GridBagConstraints.WEST;
				gbc_yesButton.insets = new Insets(0, 0, 0, 5);
				gbc_yesButton.gridx = 2;
				gbc_yesButton.gridy = 0;
				buttonPane.add(yesButton, gbc_yesButton);
				getRootPane().setDefaultButton(yesButton);

			}
			JButton noButton = new JButton("NEJ");
			noButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUI.saleConfirmation();
					dispose();

				}
			});
			noButton.setFont(new Font("Arial Narrow", Font.BOLD, 10));
			noButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			noButton.setActionCommand("Cancel");
			GridBagConstraints gbc_noButton = new GridBagConstraints();
			gbc_noButton.insets = new Insets(0, 0, 0, 5);
			gbc_noButton.gridx = 4;
			gbc_noButton.gridy = 0;
			buttonPane.add(noButton, gbc_noButton);
		}
	}

}
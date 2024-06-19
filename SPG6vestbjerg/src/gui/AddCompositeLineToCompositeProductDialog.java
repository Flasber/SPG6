package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import controller.BillableItemController;
import model.CompositeProduct;

public class AddCompositeLineToCompositeProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField barcodeTextField;
	private JTextField quantityTextField;
	private BillableItemController bctrl;
	private JLabel productBarcodeLabel;
	private JLabel quantityLabel;

	/**
	 * Create the dialog.
	 */
	public AddCompositeLineToCompositeProductDialog(ReadProductGUI rpGUI, CompositeProduct p) {
		bctrl = new BillableItemController();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);

		barcodeTextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, barcodeTextField, 28, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, barcodeTextField, -73, SpringLayout.EAST, contentPanel);
		contentPanel.add(barcodeTextField);
		barcodeTextField.setColumns(10);

		quantityTextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, quantityTextField, 25, SpringLayout.SOUTH, barcodeTextField);
		sl_contentPanel.putConstraint(SpringLayout.WEST, quantityTextField, 0, SpringLayout.WEST, barcodeTextField);
		quantityTextField.setColumns(10);
		contentPanel.add(quantityTextField);

		productBarcodeLabel = new JLabel("Stregkode:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, productBarcodeLabel, 3, SpringLayout.NORTH, barcodeTextField);
		contentPanel.add(productBarcodeLabel);

		quantityLabel = new JLabel("Antal:");
		sl_contentPanel.putConstraint(SpringLayout.WEST, quantityLabel, 64, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, productBarcodeLabel, 0, SpringLayout.WEST, quantityLabel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, quantityLabel, 0, SpringLayout.SOUTH, quantityTextField);
		contentPanel.add(quantityLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton addButton = new JButton("Tilføj");
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addCompositeLineClicked(rpGUI, p);
					}
				});
				addButton.setActionCommand("OK");
				buttonPane.add(addButton);
				getRootPane().setDefaultButton(addButton);
			}
			{
				JButton cancelButton = new JButton("Annullér");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void addCompositeLineClicked(ReadProductGUI rpGUI, CompositeProduct p) {
		bctrl.addCompositeLineToProduct(p, barcodeTextField.getText(), Integer.parseInt(quantityTextField.getText()));
		rpGUI.refresh();
		dispose();
	}

}

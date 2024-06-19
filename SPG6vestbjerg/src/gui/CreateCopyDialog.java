package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import controller.BillableItemController;
import exceptionHandling.BarcodeAlreadyExistsException;
import exceptionHandling.WarrantyInUseException;
import model.WarrantyProduct;

public class CreateCopyDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField copyIdTextField;
	private JTextField copyWarrantyTextField;
	private BillableItemController bctrl;

	/**
	 * Create the dialog.
	 */
	public CreateCopyDialog(ReadProductGUI rpGUI, WarrantyProduct p) {
		bctrl = new BillableItemController();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);

		copyIdTextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, copyIdTextField, 28, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, copyIdTextField, -73, SpringLayout.EAST, contentPanel);
		contentPanel.add(copyIdTextField);
		copyIdTextField.setColumns(10);

		copyWarrantyTextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, copyWarrantyTextField, 25, SpringLayout.SOUTH,
				copyIdTextField);
		sl_contentPanel.putConstraint(SpringLayout.WEST, copyWarrantyTextField, 0, SpringLayout.WEST, copyIdTextField);
		copyWarrantyTextField.setColumns(10);
		contentPanel.add(copyWarrantyTextField);

		JLabel copyIdLabel = new JLabel("Kopi ID:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, copyIdLabel, 3, SpringLayout.NORTH, copyIdTextField);
		contentPanel.add(copyIdLabel);

		JLabel warrantyStringLabel = new JLabel("Garantikode:");
		sl_contentPanel.putConstraint(SpringLayout.WEST, warrantyStringLabel, 64, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, copyIdLabel, 0, SpringLayout.WEST, warrantyStringLabel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, warrantyStringLabel, 0, SpringLayout.SOUTH,
				copyWarrantyTextField);
		contentPanel.add(warrantyStringLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton addButton = new JButton("Tilføj");
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addCopyClicked(rpGUI, p);
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

	private void addCopyClicked(ReadProductGUI rpGUI, WarrantyProduct p) {
		try {
			bctrl.addCopy(p, Integer.parseInt(copyIdTextField.getText()), copyWarrantyTextField.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(CreateCopyDialog.this, "Ugyldigt ID - ID skal være et tal.", "Fejl",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (BarcodeAlreadyExistsException e) {
			JOptionPane.showMessageDialog(CreateCopyDialog.this, "Kopi-ID findes allerede.", "Fejl",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (WarrantyInUseException e) {
			JOptionPane.showMessageDialog(CreateCopyDialog.this, "Garantikode findes allerede.", "Fejl",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		rpGUI.refresh();
		dispose();
	}

}

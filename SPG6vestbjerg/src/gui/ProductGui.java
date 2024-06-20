package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.BillableItemController;

public class ProductGui extends JFrame {
	private JFrame frame;
	private BillableItemController controller;
	private JButton createProductButton;
	private JButton deleteProductButton;
	private JButton backButton;
	private JButton viewProductButton;

	public ProductGui(BillableItemController controller) {
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Produktmenu");
		frame.setBounds(100, 100, 637, 384);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		createProductButton = new JButton("Opret produkt");
		createProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCreateProductDialog();
			}
		});
		panel.add(createProductButton);

		deleteProductButton = new JButton("Slet produkt");
		deleteProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDeleteProductDialog();
			}
		});
		panel.add(deleteProductButton);

		backButton = new JButton("Tilbage til hovedmenu");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openMainMenu();
			}
		});

		viewProductButton = new JButton("Se produkter");
		viewProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openViewProducts();
			}
		});

		panel.add(viewProductButton);
		panel.add(backButton);

		frame.pack();
		frame.setLocationRelativeTo(null);
	}

	private void openCreateProductDialog() {
		if (openAdminVerificationDialog()) {
			CreateProductDialog dialog = new CreateProductDialog(frame, controller, new Runnable() {
				@Override
				public void run() {
				}
			});
			dialog.setVisible(true);
		}
	}

	private void openDeleteProductDialog() {
		if (openAdminVerificationDialog()) {
			DeleteProductDialog dialog = new DeleteProductDialog(frame, controller);
			dialog.setVisible(true);
		}
	}

	private void openMainMenu() {
		frame.dispose(); // Close the ProductGui frame
		MainMenu mainMenu = new MainMenu();
		mainMenu.setVisible(true); // Open the MainMenu
	}

	public void show() {
		frame.setVisible(true);
	}

	private boolean openAdminVerificationDialog() {
		AdminVerificationDialog loginDlg = new AdminVerificationDialog(this);
		loginDlg.setVisible(true);

		return loginDlg.isSucceeded();
	}

	private void openViewProducts() {
		ReadProductsGUI rpGUI = new ReadProductsGUI();
		rpGUI.setVisible(true);
	}

}

package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BillableItemController;
import controller.CreateInStoreSaleController;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BillableItemController billableItemController;
	private CreateInStoreSaleController createInStoreSaleController;


	public MainMenu() {
		this.billableItemController = new BillableItemController();
		this.createInStoreSaleController = new CreateInStoreSaleController();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(3, 1, 10, 10));

		setContentPane(contentPane);

		JButton btnNewSale = new JButton("Nyt Salg");
		btnNewSale.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		contentPane.add(btnNewSale);

		JButton btnCreateProduct = new JButton("Produkt Menu");
		btnCreateProduct.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		contentPane.add(btnCreateProduct);

		JButton btnExit = new JButton("Afslut");
		btnExit.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		btnExit.addActionListener(e -> System.exit(0));
		contentPane.add(btnExit);

		btnNewSale.addActionListener(e -> openInStoreSaleGUI());
		btnCreateProduct.addActionListener(e -> openProductGUI());
	}

	private void openInStoreSaleGUI() {
		new InStoreSaleGUI(createInStoreSaleController);
		dispose();

	}

	private boolean openAdminVerificationDialog() {
		AdminVerificationDialog loginDlg = new AdminVerificationDialog(this);
		loginDlg.setVisible(true);

		return loginDlg.isSucceeded();
	};
			

	private void openProductGUI() {
		ProductGui productGUI = new ProductGui(billableItemController);
		productGUI.setVisible(true);
		dispose();
	}
}

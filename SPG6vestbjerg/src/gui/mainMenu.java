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
import tui.TryMe;

public class mainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BillableItemController billableItemController;
	private CreateInStoreSaleController createInStoreSaleController;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu frame = new mainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainMenu() {
		TryMe tryMe = new TryMe();
		tryMe.generateTestData();
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
		btnCreateProduct.addActionListener(e -> openAdminVerificationDialog());
	}

	private void openInStoreSaleGUI() {
		InStoreSaleGUI inStoreSaleGUI = new InStoreSaleGUI(createInStoreSaleController);
		inStoreSaleGUI.setVisible(true);
	}

	private void openAdminVerificationDialog() {
		adminVerificationDialog loginDlg = new adminVerificationDialog(this);
		loginDlg.setVisible(true);

		if (loginDlg.isSucceeded()) {
			openProductGUI();
		}
	}

	private void openProductGUI() {
		productGui productGUI = new productGui(billableItemController);
		productGUI.setVisible(true);
	}
}

package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import controller.BillableItemController;
import model.Product;

public class ReadProductsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable productTable;
	private BillableItemController bctrl;
	private ProductTableModel ptm;

	/**
	 * Create the frame.
	 */
	public ReadProductsGUI() {
		bctrl = new BillableItemController();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JButton viewProductButton = new JButton("Vis produkt");
		viewProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = productTable.getSelectedRow();
				Product p = ptm.getSelectedProduct(row);
				ReadProductGUI productView = new ReadProductGUI(p, ReadProductsGUI.this);
				productView.setVisible(true);
			}
		});
		panel.add(viewProductButton);

		JButton addProductButton = new JButton("Opret produkt");
		addProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCreateProductDialog();
			}
		});
		panel.add(addProductButton, BorderLayout.EAST);

		JButton deleteProductButton = new JButton("Slet produkt");
		deleteProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDeleteProductDialog();
			}
		});
		panel.add(deleteProductButton, BorderLayout.WEST);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		productTable = new JTable();
		productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(productTable);

		init();
	}

	public void init() {
		displayProducts();
	}

	private void displayProducts() {
		List<Product> products = bctrl.getAllProducts();
		ptm = new ProductTableModel(products);
		productTable.setModel(ptm);
	}

	private boolean openAdminVerificationDialog() {
		AdminVerificationDialog loginDlg = new AdminVerificationDialog(this);
		loginDlg.setVisible(true);

		return loginDlg.isSucceeded();
	}

	private void openCreateProductDialog() {
		if (openAdminVerificationDialog()) {
			CreateProductDialog dialog = new CreateProductDialog(this, bctrl, new Runnable() {
				@Override
				public void run() {
				}
			});
			dialog.setVisible(true);
		}
	}

	private void openDeleteProductDialog() {
		if (openAdminVerificationDialog()) {
			DeleteProductDialog dialog = new DeleteProductDialog(this, bctrl);
			dialog.setVisible(true);
		}
	}

}

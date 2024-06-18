package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.BillableItemController;
import exceptionHandling.BarcodeAlreadyExistsException;
import exceptionHandling.SkuAlreadyExistsException;
import model.CompositeLine;
import model.CompositeProduct;
import model.Price;
import model.Product;
import model.WarrantyProduct;

public class ReadProductGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldProductName;
	private JTextField textFieldProductQuantity;
	private JTextField textFieldProductPrice;
	private JTextField textFieldProductBarcode;
	private JTextField textFieldProductSKU;
	private BillableItemController bctrl;
	private ReadProductsGUI rpsGUI;
	private JButton updateProductButton;
	private Product p;
	private JList list;
	private JButton showProductOfCompositeProduct;

	/**
	 * Create the frame.
	 */
	public ReadProductGUI(Product p, ReadProductsGUI rpsGUI) {
		this.p = p;
		this.rpsGUI = rpsGUI;
		bctrl = new BillableItemController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JLabel productQuantityLabel = new JLabel("Antal på lager:");
		contentPane.add(productQuantityLabel);

		JLabel productNameLabel = new JLabel("Produktnavn:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, productQuantityLabel, 6, SpringLayout.SOUTH, productNameLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, productQuantityLabel, 0, SpringLayout.WEST, productNameLabel);
		contentPane.add(productNameLabel);

		JLabel productPriceLabel = new JLabel("Pris:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, productPriceLabel, 6, SpringLayout.SOUTH,
				productQuantityLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, productPriceLabel, 0, SpringLayout.WEST, productQuantityLabel);
		contentPane.add(productPriceLabel);

		JLabel productBarcodeLabel = new JLabel("Stregkode:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, productBarcodeLabel, 6, SpringLayout.SOUTH, productPriceLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, productBarcodeLabel, 0, SpringLayout.WEST,
				productQuantityLabel);
		contentPane.add(productBarcodeLabel);

		JLabel productSKULabel = new JLabel("SKU:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, productSKULabel, 6, SpringLayout.SOUTH, productBarcodeLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, productSKULabel, 0, SpringLayout.WEST, productQuantityLabel);
		contentPane.add(productSKULabel);

		textFieldProductName = new JTextField();
		textFieldProductName.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldProductName, -3, SpringLayout.NORTH,
				productNameLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldProductName, 80, SpringLayout.EAST, productNameLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldProductName, -10, SpringLayout.EAST, contentPane);
		contentPane.add(textFieldProductName);
		textFieldProductName.setColumns(10);
		textFieldProductName.setText(p.getName());

		textFieldProductQuantity = new JTextField();
		textFieldProductQuantity.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldProductQuantity, -3, SpringLayout.NORTH,
				productQuantityLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldProductQuantity, 0, SpringLayout.WEST,
				textFieldProductName);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldProductQuantity, 0, SpringLayout.EAST,
				textFieldProductName);
		contentPane.add(textFieldProductQuantity);
		textFieldProductQuantity.setColumns(10);
		textFieldProductQuantity.setText(String.valueOf(p.getQuantity()));

		textFieldProductPrice = new JTextField();
		textFieldProductPrice.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldProductPrice, -3, SpringLayout.NORTH,
				productPriceLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldProductPrice, 0, SpringLayout.WEST,
				textFieldProductName);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldProductPrice, 0, SpringLayout.EAST,
				textFieldProductName);
		contentPane.add(textFieldProductPrice);
		textFieldProductPrice.setColumns(10);
		textFieldProductPrice.setText(String.valueOf(p.getPrice().getPrice()));

		textFieldProductBarcode = new JTextField();
		textFieldProductBarcode.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldProductBarcode, -3, SpringLayout.NORTH,
				productBarcodeLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldProductBarcode, 0, SpringLayout.WEST,
				textFieldProductName);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldProductBarcode, 0, SpringLayout.EAST,
				textFieldProductName);
		contentPane.add(textFieldProductBarcode);
		textFieldProductBarcode.setColumns(10);
		textFieldProductBarcode.setText(p.getBarcode());

		textFieldProductSKU = new JTextField();
		textFieldProductSKU.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldProductSKU, -3, SpringLayout.NORTH, productSKULabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldProductSKU, 0, SpringLayout.WEST,
				textFieldProductName);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldProductSKU, 0, SpringLayout.EAST,
				textFieldProductName);
		contentPane.add(textFieldProductSKU);
		textFieldProductSKU.setColumns(10);
		textFieldProductSKU.setText(p.getSku());

		JLabel ProductOverviewLabel = new JLabel("Produktoversigt:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, productNameLabel, 10, SpringLayout.SOUTH,
				ProductOverviewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, ProductOverviewLabel, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, productNameLabel, 10, SpringLayout.WEST, ProductOverviewLabel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, ProductOverviewLabel, 0, SpringLayout.NORTH, contentPane);
		contentPane.add(ProductOverviewLabel);

		JPanel panel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 16, SpringLayout.SOUTH, textFieldProductSKU);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, productQuantityLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, -31, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, contentPane);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel CompositeProductListOfProductsLabel = new JLabel("Indeholder produkter:");
		panel.add(CompositeProductListOfProductsLabel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				showProductOfCompositeProduct.setEnabled(!list.isSelectionEmpty());
			}
		});

		JButton productBackButton = new JButton("Tilbage");
		productBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				rpsGUI.init();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, productBackButton, 6, SpringLayout.SOUTH, panel);
		sl_contentPane.putConstraint(SpringLayout.WEST, productBackButton, 0, SpringLayout.WEST, productQuantityLabel);
		contentPane.add(productBackButton);

		updateProductButton = new JButton("Opdatér produkt");
		updateProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateProductClicked();
				updateProductButton.setName("Gem product");
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, updateProductButton, 0, SpringLayout.NORTH, productBackButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, updateProductButton, 0, SpringLayout.EAST,
				textFieldProductName);
		contentPane.add(updateProductButton);

		JButton deleteProductButton = new JButton("Slet produkt");
		deleteProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDeleteProductDialog(p);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, deleteProductButton, 0, SpringLayout.NORTH, productBackButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, deleteProductButton, -114, SpringLayout.WEST,
				updateProductButton);
		contentPane.add(deleteProductButton);

		JCheckBox isWarrantyProductCheckbox = new JCheckBox("Garantiprodukt");
		if (p instanceof WarrantyProduct) {
			isWarrantyProductCheckbox.setSelected(true);
		}
		isWarrantyProductCheckbox.setEnabled(false);
		sl_contentPane.putConstraint(SpringLayout.EAST, deleteProductButton, 0, SpringLayout.EAST,
				isWarrantyProductCheckbox);
		sl_contentPane.putConstraint(SpringLayout.NORTH, isWarrantyProductCheckbox, -29, SpringLayout.NORTH,
				textFieldProductName);
		sl_contentPane.putConstraint(SpringLayout.WEST, isWarrantyProductCheckbox, 73, SpringLayout.EAST,
				ProductOverviewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, isWarrantyProductCheckbox, -6, SpringLayout.NORTH,
				textFieldProductName);
		sl_contentPane.putConstraint(SpringLayout.EAST, isWarrantyProductCheckbox, -124, SpringLayout.EAST,
				contentPane);
		contentPane.add(isWarrantyProductCheckbox);

		showProductOfCompositeProduct = new JButton("Vis produkt");
		showProductOfCompositeProduct.setEnabled(false);
		showProductOfCompositeProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompositeLine cl = (CompositeLine) list.getSelectedValue();
				Product p = cl.getProduct();
				ReadProductGUI rpGUI = new ReadProductGUI(p, rpsGUI);
				rpGUI.setVisible(true);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, showProductOfCompositeProduct, 6, SpringLayout.SOUTH, panel);
		sl_contentPane.putConstraint(SpringLayout.WEST, showProductOfCompositeProduct, 6, SpringLayout.EAST,
				productBackButton);
		contentPane.add(showProductOfCompositeProduct);

		displayProducts();
	}

	private boolean openAdminVerificationDialog() {
		AdminVerificationDialog loginDlg = new AdminVerificationDialog(this);
		loginDlg.setVisible(true);

		return loginDlg.isSucceeded();
	}

	private void openDeleteProductDialog(Product p) {
		if (openAdminVerificationDialog()) {
			DeleteProductDialog dialog = new DeleteProductDialog(this, bctrl, p);
			dialog.setVisible(true);
		}
	}

	private void updateProductClicked() {
		if (updateProductButton.getText().equals("Opdatér produkt")) {
			if (openAdminVerificationDialog()) {
				textFieldProductName.setEditable(true);
				textFieldProductQuantity.setEditable(true);
				textFieldProductPrice.setEditable(true);
				textFieldProductBarcode.setEditable(true);
				textFieldProductSKU.setEditable(true);
				updateProductButton.setText("Gem produkt");
			}
		} else if (updateProductButton.getText().equals("Gem produkt")) {
			textFieldProductName.setEditable(false);
			textFieldProductQuantity.setEditable(false);
			textFieldProductPrice.setEditable(false);
			textFieldProductBarcode.setEditable(false);
			textFieldProductSKU.setEditable(false);
			try {
				bctrl.updateProduct(p, p.getDescription(), textFieldProductName.getText(),
						new Price(Double.parseDouble(textFieldProductPrice.getText())), textFieldProductSKU.getText(),
						textFieldProductBarcode.getText());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BarcodeAlreadyExistsException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(new JDialog(), "Barcode findes allerede.", "Fejl",
						JOptionPane.ERROR_MESSAGE);
			} catch (SkuAlreadyExistsException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(new JDialog(), "SKU findes allerede.", "Fejl", JOptionPane.ERROR_MESSAGE);
			}
			updateProductButton.setText("Opdatér produkt");
			refresh();
		}

	}

	public void refresh() {
		textFieldProductName.setText(p.getName());
		textFieldProductQuantity.setText(String.valueOf(p.getQuantity()));
		textFieldProductPrice.setText(String.valueOf(p.getPrice().getPrice()));
		textFieldProductBarcode.setText(p.getBarcode());
		textFieldProductSKU.setText(p.getSku());
		displayProducts();
	}

	public void displayProducts() {
		if (p instanceof CompositeProduct) {
			ProductListCellRenderer cellRenderer = new ProductListCellRenderer();
			list.setCellRenderer(cellRenderer);
			List<CompositeLine> compositeLines = ((CompositeProduct) p).getCompositeLines();
			list.setListData(compositeLines.toArray());
		}

	}

}

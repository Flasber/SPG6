package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

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
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.BillableItemController;
import exceptionHandling.BarcodeAlreadyExistsException;
import exceptionHandling.SkuAlreadyExistsException;
import model.BasicProduct;
import model.CompositeLine;
import model.CompositeProduct;
import model.Price;
import model.Product;
import model.WarrantyProduct;
import model.WarrantyProduct.Copy;

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
	private JButton addProductToCompositeProduct;
	private JButton deleteCopyButton;
	private JTextField copySearchField;
	private JButton deleteProductButton;

	/**
	 * Create the frame.
	 */
	public ReadProductGUI(Product p, ReadProductsGUI rpsGUI) {
		this.p = p;
		this.rpsGUI = rpsGUI;
		bctrl = new BillableItemController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("81px"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("85px"), ColumnSpec.decode("164px"),
						ColumnSpec.decode("113px"), FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("113px"), },
				new RowSpec[] { FormSpecs.LINE_GAP_ROWSPEC, RowSpec.decode("14px"), RowSpec.decode("78px"),
						RowSpec.decode("23px"), FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("20px"),
						RowSpec.decode("20px"), RowSpec.decode("20px"), RowSpec.decode("20px"), RowSpec.decode("20px"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.PARAGRAPH_GAP_ROWSPEC, RowSpec.decode("64px"),
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("23px"), }));

		JCheckBox isWarrantyProductCheckbox = new JCheckBox("Garantiprodukt");
		contentPane.add(isWarrantyProductCheckbox, "4, 2, 2, 1");
		isWarrantyProductCheckbox.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "2, 3, 7, 5");

		list = new JList<>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		deleteCopyButton = new JButton("Slet kopi");
		scrollPane.setRowHeaderView(deleteCopyButton);
		deleteCopyButton.setEnabled(false);

		JPanel panel_1 = new JPanel();
		scrollPane.setColumnHeaderView(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel copySearchLabel = new JLabel("Søg:");
		panel_1.add(copySearchLabel);

		copySearchField = new JTextField();
		panel_1.add(copySearchField);
		copySearchField.setColumns(10);

		JLabel CompositeProductListOfProductsLabel = new JLabel("Indeholder produkter:");
		panel_1.add(CompositeProductListOfProductsLabel);

		copySearchField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				displayFilteredProducts();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				displayFilteredProducts();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				displayFilteredProducts();
			}

		});

		deleteCopyButton.setVisible(false);
		deleteCopyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WarrantyProduct.Copy c = (Copy) list.getSelectedValue();
				deleteCopyClicked((WarrantyProduct) p, c);
			}
		});
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (list.isSelectionEmpty()) {
					deleteProductButton.setText("Slet produkt");
				} else {
					deleteProductButton.setText("fjern produkt fra samleprodukt");
				}
				if (p instanceof CompositeProduct) {
					showProductOfCompositeProduct.setEnabled(!list.isSelectionEmpty());
				}
				if (p instanceof WarrantyProduct) {
					deleteCopyButton.setEnabled(true);
					deleteCopyButton.setVisible(true);
				}
			}
		});

		JLabel ProductOverviewLabel = new JLabel("Produktoversigt:");
		contentPane.add(ProductOverviewLabel, "2, 2, left, top");

		if (p instanceof BasicProduct) {
			scrollPane.setVisible(false);
			CompositeProductListOfProductsLabel.setVisible(false);
		}

		if (p instanceof WarrantyProduct) {
			JButton addCopyToWarrantyProduct = new JButton("Tilføj kopi af garantiprodukt");
			addCopyToWarrantyProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openCreateCopyDialog((WarrantyProduct) p);
				}
			});
		}

		if (p instanceof CompositeProduct) {
			addProductToCompositeProduct = new JButton("Tilføj produkt til samleprodukt");
			addProductToCompositeProduct.setEnabled(false);
			contentPane.add(addProductToCompositeProduct, "8, 16, left, top");
			addProductToCompositeProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addProductToCompositeProductClicked((CompositeProduct) p);
				}
			});
		}

		JLabel productNameLabel = new JLabel("Produktnavn:");
		contentPane.add(productNameLabel, "2, 8, center, center");

		textFieldProductName = new JTextField();
		textFieldProductName.setEditable(false);
		contentPane.add(textFieldProductName, "4, 8, 5, 1, fill, top");
		textFieldProductName.setColumns(10);
		textFieldProductName.setText(p.getName());

		JLabel productQuantityLabel = new JLabel("Antal på lager:");
		contentPane.add(productQuantityLabel, "2, 9, right, center");

		textFieldProductQuantity = new JTextField();
		textFieldProductQuantity.setEditable(false);
		contentPane.add(textFieldProductQuantity, "4, 9, 5, 1, fill, top");
		textFieldProductQuantity.setColumns(10);
		textFieldProductQuantity.setText(String.valueOf(p.getQuantity()));

		JLabel productPriceLabel = new JLabel("Pris:");
		contentPane.add(productPriceLabel, "2, 10, center, center");

		textFieldProductPrice = new JTextField();
		textFieldProductPrice.setEditable(false);
		contentPane.add(textFieldProductPrice, "4, 10, 5, 1, fill, top");
		textFieldProductPrice.setColumns(10);
		textFieldProductPrice.setText(String.valueOf(p.getPrice().getPrice()));

		JLabel productBarcodeLabel = new JLabel("Stregkode:");
		contentPane.add(productBarcodeLabel, "2, 12, center, center");

		textFieldProductBarcode = new JTextField();
		textFieldProductBarcode.setEditable(false);
		contentPane.add(textFieldProductBarcode, "4, 12, 5, 1, fill, top");
		textFieldProductBarcode.setColumns(10);
		textFieldProductBarcode.setText(p.getBarcode());

		JLabel productSKULabel = new JLabel("SKU:");
		contentPane.add(productSKULabel, "2, 14, center, center");

		textFieldProductSKU = new JTextField();
		textFieldProductSKU.setEditable(false);
		contentPane.add(textFieldProductSKU, "4, 14, 5, 1, fill, top");
		textFieldProductSKU.setColumns(10);
		textFieldProductSKU.setText(p.getSku());

		JButton productBackButton = new JButton("Tilbage");
		productBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				rpsGUI.init();
			}
		});
		contentPane.add(productBackButton, "2, 16, center, top");

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
		contentPane.add(showProductOfCompositeProduct, "4, 16, left, top");

		deleteProductButton = new JButton("Slet produkt");
		deleteProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.isSelectionEmpty()) {
					openDeleteProductDialog(p);
				} else {

					CompositeLine cl = (CompositeLine) list.getSelectedValue();
					removeProductClicked((CompositeProduct) p, cl);

				}

			}
		});
		contentPane.add(deleteProductButton, "5, 16, fill, top");

		updateProductButton = new JButton("Opdatér produkt");
		updateProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateProductClicked();
				updateProductButton.setName("Gem produkt");
			}
		});
		contentPane.add(updateProductButton, "6, 16, left, top");

		deleteProductButton.setEnabled(false);

		if (p instanceof WarrantyProduct) {
			isWarrantyProductCheckbox.setSelected(true);
		}

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
				deleteProductButton.setEnabled(true);
				updateProductButton.setText("Gem produkt");
				if (p instanceof CompositeProduct) {
					addProductToCompositeProduct.setEnabled(true);
				}
			}
		} else if (updateProductButton.getText().equals("Gem produkt")) {
			textFieldProductName.setEditable(false);
			textFieldProductQuantity.setEditable(false);
			textFieldProductPrice.setEditable(false);
			textFieldProductBarcode.setEditable(false);
			textFieldProductSKU.setEditable(false);
			deleteProductButton.setEnabled(false);
			if (p instanceof CompositeProduct) {
				addProductToCompositeProduct.setEnabled(false);
			}

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
		} else if (p instanceof WarrantyProduct) {
			CopyListCellRenderer cellRenderer = new CopyListCellRenderer();
			list.setCellRenderer(cellRenderer);
			List<WarrantyProduct.Copy> copies = ((WarrantyProduct) p).getCopies();
			list.setListData(copies.toArray());
		}
	}

	private void displayFilteredProducts() {
		String filter = copySearchField.getText().toLowerCase();
		if (p instanceof WarrantyProduct) {
			List<WarrantyProduct.Copy> filteredCopies = ((WarrantyProduct) p).getCopies().stream()
					.filter(copy -> copy.toString().toLowerCase().contains(filter)).collect(Collectors.toList());
			list.setListData(filteredCopies.toArray());
		} else if (p instanceof CompositeProduct) {
			List<CompositeLine> filteredLines = ((CompositeProduct) p).getCompositeLines().stream()
					.filter(line -> line.toString().toLowerCase().contains(filter)).collect(Collectors.toList());
			list.setListData(filteredLines.toArray());
		}
	}

	private void openCreateCopyDialog(WarrantyProduct p) {
		if (openAdminVerificationDialog()) {
			CreateCopyDialog ccd = new CreateCopyDialog(this, p);
			ccd.setVisible(true);
		}

	}

	private void deleteCopyClicked(WarrantyProduct p, WarrantyProduct.Copy c) {
		if (openAdminVerificationDialog()) {
			bctrl.deleteCopy(p, c);
			refresh();
		}
	}

	public void addProductToCompositeProductClicked(CompositeProduct cp) {
		AddCompositeLineToCompositeProductDialog aptp = new AddCompositeLineToCompositeProductDialog(this, cp);
		aptp.setVisible(true);
		refresh();
	}

	public void removeProductClicked(CompositeProduct cp, CompositeLine cl) {
		bctrl.removeCompositeLineFromProduct(cp, cl);
		refresh();
	}
}

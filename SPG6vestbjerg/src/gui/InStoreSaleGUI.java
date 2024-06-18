package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.CreateInStoreSaleController;
import exceptionHandling.AddWarrantyProductException;
import exceptionHandling.UnexpectedClassException;
import model.BillableItem;
import model.BillableLine;
import model.BusinessCustomer;
import model.Customer;
import model.InStoreSale;
import model.PrivateCustomer;

public class InStoreSaleGUI extends JFrame {
    private CreateInStoreSaleController controller;
    private JFrame frame;

    private int employeeID;
    private int registerNO;
    public boolean isLoggedIn;

    public InStoreSaleGUI(CreateInStoreSaleController controller) {
        this.controller = controller;
        frame = new JFrame("InStore Sale");
        frame.setSize(400, 300);
        if (!isRunningInWindowBuilder()) {
            resetUI();
        }
        frame.setVisible(true);
    }

    private boolean isRunningInWindowBuilder() {
        return System.getProperty("java.class.path").contains("eclipse") && System.getProperty("sun.java.command")
                .contains("org.eclipse.jdt.internal.junit.runner.RemoteTestRunner");
    }

    public void resetUI() {
        frame.getContentPane().removeAll();
        if (!isLoggedIn) {
            createInStoreSale();
        } else {
            checkIfLoggedIn();
        }
        frame.revalidate();
        frame.repaint();
    }

    private void checkIfLoggedIn() {
        controller.createInStoreSale(registerNO, employeeID);
        addItemToSale();
    }

    private void createInStoreSale() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2));
        JLabel registerNoLabel = new JLabel("Kassenummer:");
        registerNoLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        JTextField registerNoField = new JTextField();
        JLabel employeeIdLabel = new JLabel("Medarbejder Nummer:");
        employeeIdLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        JTextField employeeIdField = new JTextField();
        JButton nextButton = new JButton("NÆSTE");
        nextButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));

        fieldsPanel.add(registerNoLabel);
        fieldsPanel.add(registerNoField);
        fieldsPanel.add(employeeIdLabel);
        fieldsPanel.add(employeeIdField);
        fieldsPanel.add(new JLabel()); // Empty label to fill the grid

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightButtonPanel.add(nextButton);
        buttonPanel.add(rightButtonPanel, BorderLayout.EAST);
        buttonPanel.add(leftButtonPanel, BorderLayout.WEST);

        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        addCancelButton(leftButtonPanel);

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerNO = Integer.parseInt(registerNoField.getText());
                employeeID = Integer.parseInt(employeeIdField.getText());

                frame.getContentPane().removeAll();
                isLoggedIn = true;
                checkIfLoggedIn();
            }
        });
    }

    private void addItemToSale() {
		frame.setSize(600, 300); // Adjusted the frame size to fit all buttons properly
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldsPanel = new JPanel(new GridLayout(4, 2));
		JLabel barcodeLabel = new JLabel("Indtast stregkode:");
		barcodeLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		JTextField barcodeField = new JTextField();
		JLabel quantityLabel = new JLabel("Indtast antal:");
		quantityLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		JTextField quantityField = new JTextField();
		JButton addButton = new JButton("TILFØJ");
		addButton.setFont(new Font("Arial Narrow", Font.BOLD, 20)); // Adjusted font size
		JButton finishButton = new JButton("FÆRDIG");
		finishButton.setFont(new Font("Arial Narrow", Font.BOLD, 20)); // Adjusted font size
		JButton logOutButton = new JButton("LOG UD");
		logOutButton.setFont(new Font("Arial Narrow", Font.BOLD, 20)); // Adjusted font size
	
		fieldsPanel.add(barcodeLabel);
		fieldsPanel.add(barcodeField);
		fieldsPanel.add(quantityLabel);
		fieldsPanel.add(quantityField);
		fieldsPanel.add(new JLabel()); // Empty label to fill the grid
	
		JPanel buttonPanel = new JPanel(new BorderLayout());
		JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel centerButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
		// Add the cancel button first, then the log out button
		addCancelButton(leftButtonPanel);
		leftButtonPanel.add(logOutButton);
		centerButtonPanel.add(addButton);
		rightButtonPanel.add(finishButton);
	
		buttonPanel.add(leftButtonPanel, BorderLayout.WEST);
		buttonPanel.add(centerButtonPanel, BorderLayout.CENTER);
		buttonPanel.add(rightButtonPanel, BorderLayout.EAST);
	
		panel.add(fieldsPanel, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
	
		frame.getContentPane().add(panel);
		frame.revalidate();
		frame.repaint();
	
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String barcode = barcodeField.getText();
					int quantity = Integer.parseInt(quantityField.getText());
					BillableItem addedItem = controller.addItemToSale(barcode, quantity);
	
					if (addedItem == null) {
						JOptionPane.showMessageDialog(frame, "Produkt med stregkode " + barcode + " blev ikke fundet.",
								"Fejl", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frame,
								"Produkt tilføjet: " + addedItem.getName() + " " + addedItem.getPrice().getPrice(),
								"Succes", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (AddWarrantyProductException ae) {
					JOptionPane.showMessageDialog(frame,
							"Garantiprodukter skal ikke skannes med produktstregkoden. Scan venligst kopistregkoden.",
							"Fejl", JOptionPane.ERROR_MESSAGE);
				} catch (UnexpectedClassException uce) {
					JOptionPane.showMessageDialog(frame, "Fejl ved tilføjelse af produkt: " + uce.getMessage(), "Fejl",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Der opstod en fejl: " + ex.getMessage(), "Fejl",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	
		finishButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				isCustomer();
			}
		});
	
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isLoggedIn = false;
				resetUI();
			}
		});
	}
	
	

    public void isCustomer() {
        IsCustomerDialog dialog = new IsCustomerDialog(this);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    public void addCustomerToSale() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2));
        JLabel phoneLabel = new JLabel("<html>Indtast<br>kundens telefonnummer:</html>");
        phoneLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        JTextField phoneField = new JTextField();
        JButton addButton = new JButton("Tilføj");
        addButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));

        fieldsPanel.add(phoneLabel);
        fieldsPanel.add(phoneField);
        fieldsPanel.add(new JLabel()); // Empty label to fill the grid

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightButtonPanel.add(addButton);
        buttonPanel.add(rightButtonPanel, BorderLayout.EAST);
        buttonPanel.add(leftButtonPanel, BorderLayout.WEST);

        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        addCancelButton(leftButtonPanel);

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = phoneField.getText();
                Customer customer = controller.addCustomerToSale(phoneNumber);

                if (customer != null) {
                    if (customer instanceof PrivateCustomer) {
                        JOptionPane.showMessageDialog(frame,
                                "Privatkunde blev fundet: " + customer.getName() + " - " + customer.getTlf() + " - "
                                        + ((PrivateCustomer) customer).getEmail(),
                                "Succes", JOptionPane.INFORMATION_MESSAGE);
                    } else if (customer instanceof BusinessCustomer) {
                        JOptionPane.showMessageDialog(frame,
                                "Erhvervskunde blev fundet: " + customer.getName() + " - " + customer.getTlf() + " - "
                                        + ((BusinessCustomer) customer).getCvr(),
                                "Succes", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Kunde med telefonnummer " + phoneNumber + " blev ikke fundet.", "Fejl",
                            JOptionPane.ERROR_MESSAGE);
                }
                frame.getContentPane().removeAll();
                checkIfPaid();
            }
        });
    }

    public void checkIfPaid() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel totalLabel = new JLabel();
		JButton payButton = new JButton("BETAL");
		payButton.setFont(new Font("Arial Narrow", Font.BOLD, 38)); // Making the button bigger
	
		InStoreSale s = controller.getSaleinProgress();
		BigDecimal b = s.getTotal();
		String string = b.toString();
		totalLabel.setText("Totalbeløb: " + string + " kr");
	
		panel.add(totalLabel, BorderLayout.CENTER);
	
		// Create a button panel with FlowLayout
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addCancelButton(buttonPanel); // Add cancel button to the left
		buttonPanel.add(payButton); // Add pay button next to it
	
		panel.add(buttonPanel, BorderLayout.SOUTH);
	
		frame.getContentPane().add(panel);
		frame.revalidate();
		frame.repaint();
	
		var theGui = this;
	
		payButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InStoreSale sale = null;
				try {
					sale = controller.isPaid();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame,
							"Kan ikke afslutte salget med garantiprodukt: " + ex.getMessage(), "Fejl",
							JOptionPane.ERROR_MESSAGE);
				}
	
				if (sale != null) {
					JOptionPane.showMessageDialog(frame, "Betaling bekræftet", "Succes",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "Betaling afvist", "Fejl", JOptionPane.ERROR_MESSAGE);
				}
				frame.getContentPane().removeAll();
				ReceiptConfirmation dialog = new ReceiptConfirmation(theGui);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
	}
	

    public void printReceipt() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        InStoreSale sale = controller.getSaleInProgress();

        if (sale != null) {
            StringBuilder receipt = new StringBuilder();
            receipt.append("Kvittering\n");
            receipt.append("================================\n");

            for (BillableLine bl : sale.getBillableLines()) {
                receipt.append(bl.toString()).append(" ").append(bl.getSubTotal()).append(" ,-\n");
            }

            receipt.append("================================\n");
            receipt.append("Totalbeløb: ").append(sale.getTotal()).append(" ,-\n");

            receipt.append("""
                    ,
                     /(  ___
                    |  >:===========`
                     )(
                     ""
                    """);

            receiptArea.setText(receipt.toString());
        } else {
            receiptArea.setText("Ingen salg fundet.");
        }

        panel.add(new JScrollPane(receiptArea), BorderLayout.CENTER);

        JButton newSaleButton = new JButton("Nyt salg");
        newSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetUI();
            }
        });

        panel.add(newSaleButton, BorderLayout.SOUTH);
        addCancelButton(panel, BorderLayout.NORTH);

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    public void saleConfirmation() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel newLabel = new JLabel("Salget er gennemført");
        JButton okButton = new JButton("OK");

        panel.add(newLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightButtonPanel.add(okButton);
        buttonPanel.add(rightButtonPanel, BorderLayout.EAST);
        buttonPanel.add(leftButtonPanel, BorderLayout.WEST);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        addCancelButton(leftButtonPanel);

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetUI();
            }
        });
    }

    private void addCancelButton(JPanel panel) {
        addCancelButton(panel, BorderLayout.WEST);
    }

    private void addCancelButton(JPanel panel, String layoutPosition) {
        JButton cancelButton = new JButton("AFBRYD");
        cancelButton.setFont(new Font("Arial Narrow", Font.BOLD, 18));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
                frame.dispose();
            }
        });

        JPanel cancelButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cancelButtonPanel.add(cancelButton);

        if (layoutPosition == null) {
            panel.add(cancelButtonPanel, BorderLayout.WEST);
        } else {
            panel.add(cancelButtonPanel, layoutPosition);
        }
    }
}

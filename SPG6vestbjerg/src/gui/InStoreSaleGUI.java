package gui;

import java.awt.BorderLayout;
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
        frame.setVisible(true);
        resetUI();
    }

    public void resetUI() {
        frame.getContentPane().removeAll();
        if (isLoggedIn == false) {
            createInStoreSale();
        } else if (isLoggedIn == true) {
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
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel registerNoLabel = new JLabel("Kassenummer:");
        registerNoLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        JTextField registerNoField = new JTextField();
        JLabel employeeIdLabel = new JLabel("Medarbejder Nummer:");
        employeeIdLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        JTextField employeeIdField = new JTextField();
        JButton nextButton = new JButton("NÆSTE");
        nextButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));

        panel.add(registerNoLabel);
        panel.add(registerNoField);
        panel.add(employeeIdLabel);
        panel.add(employeeIdField);

        addCancelButton(panel);

        panel.add(new JLabel());
        panel.add(nextButton);

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerNO = Integer.parseInt(registerNoField.getText());
                employeeID = Integer.parseInt(employeeIdField.getText());

                frame.getContentPane().removeAll();
                checkIfLoggedIn();
                isLoggedIn = true;
            }
        });
    }

    private void addItemToSale() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JLabel barcodeLabel = new JLabel("Indtast stregkode:");
        barcodeLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        JTextField barcodeField = new JTextField();
        JLabel quantityLabel = new JLabel("Indtast antal:");
        quantityLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        JTextField quantityField = new JTextField();
        JButton addButton = new JButton("TILFØJ");
        addButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));
        JButton finishButton = new JButton("FÆRDIG");
        finishButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));
        JButton logOutButton = new JButton("LOG UD");
        logOutButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));

        panel.add(barcodeLabel);
        panel.add(barcodeField);
        panel.add(quantityLabel);
        panel.add(quantityField);

        addCancelButton(panel);

        panel.add(addButton);
        panel.add(finishButton);
        panel.add(logOutButton);

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
        // Here we create and show the dialog
        IsCustomerDialog dialog = new IsCustomerDialog(this); // 'this' refers to the current instance of InStoreSaleGUI
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    public void addCustomerToSale() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel phoneLabel = new JLabel("<html>Indtast<br>kundens telefonnummer:</html>");
        phoneLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        JTextField phoneField = new JTextField();
        JButton addButton = new JButton("Tilføj");
        addButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));

        panel.add(phoneLabel);
        panel.add(phoneField);

        addCancelButton(panel);

        panel.add(new JLabel());
        panel.add(addButton);

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
                                "Privatkunde blev fundet: " + customer.getName() + " - " + customer.getTlf() + " - " + ((PrivateCustomer) customer).getEmail(), "Succes",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (customer instanceof BusinessCustomer) {
                        JOptionPane.showMessageDialog(frame,
                                "Erhvervskunde blev fundet: " + customer.getName() + " - " + customer.getTlf() + " - " + ((BusinessCustomer) customer).getCvr(), "Succes",
                                JOptionPane.INFORMATION_MESSAGE);
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
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JButton payButton = new JButton("Betal");

        InStoreSale s = controller.getSaleinProgress();
        BigDecimal b = s.getTotal();
        String string = b.toString();

        panel.add(new JLabel("Totalbeløb: " + string + " kr"));

        addCancelButton(panel);

        panel.add(payButton);

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

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    public void saleConfirmation() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel newLabel = new JLabel("Salget er gennemført");
        JButton okButton = new JButton("OK");

        panel.add(newLabel);

        addCancelButton(panel);

        panel.add(okButton);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetUI();
            }
        });

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void addCancelButton(JPanel panel) {
        addCancelButton(panel, null);
    }

    private void addCancelButton(JPanel panel, String layoutPosition) {
        JButton cancelButton = new JButton("AFBRYD");
        cancelButton.setFont(new Font("Arial Narrow", Font.BOLD, 38));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
                frame.dispose();
            }
        });

        if (layoutPosition == null) {
            panel.add(cancelButton);
        } else {
            panel.add(cancelButton, layoutPosition);
        }
    }
}

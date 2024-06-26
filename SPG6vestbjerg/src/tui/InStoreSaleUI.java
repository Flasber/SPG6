package tui;

import java.util.ArrayList;
import java.util.Scanner;

import controller.CreateInStoreSaleController;
import exceptionHandling.AddWarrantyProductException;
import exceptionHandling.UnexpectedClassException;
import model.BillableItem;
import model.BillableLine;
import model.Customer;
import model.InStoreSale;

public class InStoreSaleUI {
	private CreateInStoreSaleController controller;

	public InStoreSaleUI(CreateInStoreSaleController controller) {
		this.controller = controller;
	}

	private void println(String s) {
		System.out.println(s);
	}

	private void print(String s) {
		System.out.print(s);
	}

	/**
	 * Start of UI
	 * 
	 * @throws Exception if an error occurs during starting the sale process
	 */
	public static void start() throws Exception {
		CreateInStoreSaleController controller = new CreateInStoreSaleController();
		InStoreSaleUI ui = new InStoreSaleUI(controller);
		TryMe tryMe = new TryMe();
		tryMe.generateTestData();
		ui.inStoreSaleUI();
	}

	/**
	 * the UI for inStoreSale
	 * 
	 * @throws Exception if an error occurs during starting the sale process
	 */
	private void inStoreSaleUI() throws Exception {
		Scanner scanner = new Scanner(System.in);

		createInStoreSale(scanner);
		addItemToSale(scanner);
		addCustomerToSale(scanner);
		checkIfPaid();
		printReceipt();

		println("\nSalget er lavet.");
	}

	/**
	 * method to create in-store sale
	 * 
	 * @param scanner for user input
	 */
	private void createInStoreSale(Scanner scanner) {
		println("\nLaver et salg...");
		print("Kassenummer");
		int registerNo = scanner.nextInt();
		print("Medarbejder Nummer: ");
		int employeeId = scanner.nextInt();
		controller.createInStoreSale(registerNo, employeeId);
	}

	/**
	 * The addItemToSale method
	 * 
	 * @param scanner for user input
	 * @throws Exception if an error occurs during adding an item to sale
	 */
	private void addItemToSale(Scanner scanner) throws Exception {
		println("\nTilføjer produkter til salget...");

		boolean addingItems = true;
		while (addingItems) {
			print("Indtast stregkode: ");
			String barcode = scanner.next();
			print("Indtast antal: ");
			int quantity = scanner.nextInt();

			try {

				BillableItem addedItem = controller.addItemToSale(barcode, quantity);

				if (addedItem == null) {
					println("Produkt med stregkode " + barcode + " blev ikke fundet.");
				} else {
					println("Produkt tilføjet: " + addedItem.getName() + " @ " + addedItem.getPrice().getPrice());
				}
			} catch (AddWarrantyProductException ae) {
				println("Garantiprodukter skal ikke skannes med produktstregkoden. Scan venlights kopistregkoden.");
			} catch (UnexpectedClassException e) {
				println("Fejl ved tilføjelse af produkt: " + e.getMessage());
			}

			print("Vil du tilføje flere varer? (ja/nej): ");
			String choice = scanner.next().toLowerCase();
			if (!choice.equals("ja")) {
				addingItems = false;
			}
		}
	}

	/**
	 * Adding a customer to sale
	 * 
	 * @param scanner for user input
	 */
	private void addCustomerToSale(Scanner scanner) {
		println("\nTilføj Kunder...");
		print("Indtast Kundens telefonnummer: ");
		String phoneNumber = scanner.next();
		Customer customer = controller.addCustomerToSale(phoneNumber);

		if (customer != null) {
			println("Kunden blev fundet: " + customer.getName() + " - " + customer.getTlf());
		} else {
			println("Kunde med telefonnummer " + phoneNumber + " blev ikke fundet.");
		}
	}

	/**
	 * Checking if the sale is paid
	 */
	private void checkIfPaid() {
		println("\nBetaling....");
		InStoreSale sale = null;
		try {
			sale = controller.isPaid();
		} catch (Exception e) {
			System.err.println("can not finish sale wiht warrantyProduct");
			e.printStackTrace();
		}
		if (sale != null) {
			println("Betaling bekræftet");

		} else {
			println("Betaling afvist");
		}

	}

	/**
	 * Method to print the receipt
	 */
	private void printReceipt() {
		println("Vil du have en kvittering? (ja/nej)");
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.next();

		if (answer.toLowerCase().trim().equals("ja")) {
			InStoreSale sale = controller.getLastSale();
			ArrayList<BillableLine> billableLines = (ArrayList<BillableLine>) sale.getBillableLines();
			println("\uD83D\uDD28");
			for (BillableLine bl : billableLines) {
				println(bl.toString() + " " + bl.getSubTotal() + " ,-");
			}
			println("Totalbeløb: " + sale.getTotal() + " ,-");
			println("""
					,
					 /(  ___
					|  >:===========`
					 )(
					 ""
					""");
		} else if (answer.toLowerCase().trim().equals("nej")) {
			println("Okay! Fortsat god dag!");
		}

	}

}

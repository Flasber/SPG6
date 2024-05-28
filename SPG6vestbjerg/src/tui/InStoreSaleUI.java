package tui;

import java.util.Scanner;

import controller.CreateInStoreSaleController;
import model.BillableItem;
import model.Customer;
import model.InStoreSale.AddWarrantyProductException;

public class InStoreSaleUI {
	private CreateInStoreSaleController controller;

	public InStoreSaleUI(CreateInStoreSaleController controller) {
		this.controller = controller;
	}

	private void println(String s) {
		System.out.println(s);
	}

	private void print(String s) {
		System.out.println(s);
	}

	public void start() {
		TryMe tryMe = new TryMe();
		tryMe.generateTestData();
		inStoreSaleUI();
	}

	private int writeInStoreSaleUI() {
		Scanner keyboard = new Scanner(System.in);
		println("******InStoreSaleUI******");
		println(" (1) Opret salg");
		println(" (0) Tilbage");
		print("\n Vælg:");
		int choice = getIntegerFromUser(keyboard);
		return choice;
	}

	private int getIntegerFromUser(Scanner keyboard) {
		while (!keyboard.hasNextInt()) {
			println("Input skal være et tal - prøv igen");
			keyboard.nextLine();
		}
		return keyboard.nextInt();
	}

	private void inStoreSaleUI() {
		Scanner scanner = new Scanner(System.in);
		boolean saleRunning = true;
		int step = 1;

		while (saleRunning) {
			switch (step) {
			case 1:
				createInStoreSale(scanner);
				step++;
				break;
			case 2:
				addItemToSale(scanner);
				step++;
				break;
			case 3:
				addCustomerToSale(scanner);
				step++;
				break;
			case 4:
				checkIfPaid();
				step++;
				break;
			case 5:
				// TODO recipt
			default:
				println("Tast noget andet");
				saleRunning = false;
				break;
			}
		}

		println("\nSalget er lavet.");
	}

	private void createInStoreSale(Scanner scanner) {
		println("\nLaver et salg...");
		print("Kassenummer");
		int registerNo = scanner.nextInt();
		print("Medarbejder Nummer: ");
		int employeeId = scanner.nextInt();
		controller.createInStoreSale(registerNo, employeeId);
	}

	private void addItemToSale(Scanner scanner) {
		println("\nTilføjer produkter til salget...");

		boolean addingItems = true;
		while (addingItems) {
			print("Indtast stregkode: ");
			String barcode = scanner.next();
			print("Indtast antal: ");
			int quantity = scanner.nextInt();

			try {
				//
				BillableItem addedItem = controller.addItemToSale(barcode, quantity);

				//
				if (addedItem == null) {
					println("Produkt med stregkode " + barcode + " blev ikke fundet.");
				} else {
					println("Produkt tilføjet: " + addedItem.getName() + " @ " + addedItem.getPrice().getPrice());
				}
			} catch (AddWarrantyProductException ae) {
				println("Garantiprodukter skal ikke skannes med produktstregkoden. Scan venlights kopistregkoden.");
			} catch (Exception e) {
				println("Fejl ved tilføjelse af produkt: " + e.getMessage());
			}

			print("Vil du tilføje flere varer? (ja/nej): ");
			String choice = scanner.next().toLowerCase();
			if (!choice.equals("ja")) {
				addingItems = false;
			}
		}
	}

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

	private void checkIfPaid() {
		println("\nBetaling....");
		if (controller.isPaid() != null) {
			println("Betaling bekræftet");
		} else {
			println("Betaling afvist");
		}
	}

	public static void main(String[] args) {
		CreateInStoreSaleController controller = new CreateInStoreSaleController();
		InStoreSaleUI ui = new InStoreSaleUI(controller);
		ui.start();
	}
}

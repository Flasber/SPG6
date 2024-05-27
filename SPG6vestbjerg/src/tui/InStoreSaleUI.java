package tui;

import java.util.Scanner;

import controller.CreateInStoreSaleController;
import model.BillableItem;
import model.BillableItemContainer;
import model.Customer;
import model.WarrantyProduct;

public class InStoreSaleUI {
	private CreateInStoreSaleController controller;

	public InStoreSaleUI(CreateInStoreSaleController controller) {
		this.controller = controller;
	}

	public void start() {
		TryMe tryMe = new TryMe();
		tryMe.generateTestData();
		InStoreSaleUI();
	}

	private int writeInStoreSaleUI() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("******InStoreSaleUI******");
		System.out.println(" (1) Opret salg");
		System.out.println(" (0) Tilbage");
		System.out.print("\n Vælg:");
		int choice = getIntegerFromUser(keyboard);
		return choice;
	}

	private int getIntegerFromUser(Scanner keyboard) {
		while (!keyboard.hasNextInt()) {
			System.out.println("Input skal være et tal - prøv igen");
			keyboard.nextLine();
		}
		return keyboard.nextInt();
	}

	private void InStoreSaleUI() {
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
				System.out.println("Tast noget andet");
				saleRunning = false;
				break;
			}
		}

		System.out.println("\nSalget er lavet.");
	}

	private void createInStoreSale(Scanner scanner) {
		System.out.println("\nLaver et salg...");
		System.out.print("Kassenummer");
		int registerNo = scanner.nextInt();
		System.out.print("Medarbejder Nummer: ");
		int employeeId = scanner.nextInt();
		controller.createInStoreSale(registerNo, employeeId);
	}

	private void addItemToSale(Scanner scanner) {
		System.out.println("\nTilføjer produkter til salget...");

		boolean addingItems = true;
		while (addingItems) {
			System.out.print("Indtast stregkode: ");
			String barcode = scanner.next();

			try {
				System.out.print("Indtast antal: ");
				int quantity = scanner.nextInt();

				BillableItem billableItem = null;
				WarrantyProduct.Copy warrantyCopy = BillableItemContainer.getInstance().findCopy(barcode);
				if (warrantyCopy != null) {
					billableItem = warrantyCopy;
				} else {
					BillableItem basicProduct = BillableItemContainer.getInstance().findProduct(barcode);
					if (basicProduct != null) {
						billableItem = basicProduct;
					}
				}

				if (billableItem != null) {

					System.out.println("Produkt tilføjet: " + billableItem.getName() + " - " + billableItem.getPrice());
				} else {
					System.out.println("Produkt med stregkode " + barcode + " blev ikke fundet.");
				}
			} catch (Exception e) {
				System.out.println("Fejl ved tilføjelse af produkt: " + e.getMessage());
			}

			System.out.print("Vil du tilføje flere varer? (ja/nej): ");
			String choice = scanner.next().toLowerCase();
			if (!choice.equals("ja")) {
				addingItems = false;
			}
		}
	}

	private void addCustomerToSale(Scanner scanner) {
		System.out.println("\nTilføj Kunder...");
		System.out.print("Indtast Kundens telefonnummer: ");
		String phoneNumber = scanner.next();
		Customer customer = controller.addCustomerToSale(phoneNumber);

		if (customer != null) {
			System.out.println("Kunden blev fundet: " + customer.getName() + " - " + customer.getTlf());
		} else {
			System.out.println("Kunde med telefonnummer " + phoneNumber + " blev ikke fundet.");
		}
	}

	private void checkIfPaid() {
		System.out.println("\nBetaling....");
		if (controller.isPaid() != null) {
			System.out.println("Betaling bekræftet");
		} else {
			System.out.println("Betaling afvist");
		}
	}

	public static void main(String[] args) {
		CreateInStoreSaleController controller = new CreateInStoreSaleController();
		InStoreSaleUI ui = new InStoreSaleUI(controller);
		ui.start();
	}
}

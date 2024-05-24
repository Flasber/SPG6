package tui;

import java.util.Scanner;
import controller.CreateInStoreSaleController;
import model.Product;

public class InStoreSaleUI {
	private CreateInStoreSaleController controller;

	public InStoreSaleUI(CreateInStoreSaleController controller) {
		this.controller = controller;
	}

	public void start() {
		InStoreSaleUI();
	}

	private void InStoreSaleUI() {
		boolean running = true;
		while (running) {
			int choice = writeInStoreSaleUI();
			switch (choice) {
			case 1:
				System.out.println(" Denne er ikke implementeret endnu!");
				break;
			case 0:
				running = false;
				break;
			default:
				System.out.println("En uforklarlig fejl er sket med choice = " + choice);
				break;
			}
		}
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

	private void createSaleFlow() {
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
				addProductToSale(scanner);
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

	private void addProductToSale(Scanner scanner) {
		System.out.println("\nTilføjer produkter til salget...");

		boolean addingProducts = true;
		while (addingProducts) {
			System.out.print("indtast barcode her: ");
			String barcode = scanner.next();

			Product product = controller.findProductByBarcode(barcode);

			if (product != null) {
				System.out.println("Produkt fundet: " + product.getName() + " - " + product.getPrice());
				controller.addProductToSale(product);
			} else {
				System.out.println("Produkt med barcode " + barcode + " not found.");
			}

			System.out.print("vil du gerne tilføje flere varer): ja/nej ");
			String choice = scanner.next().toLowerCase();
			if (!choice.equals("ja")) {
				{
					addingProducts = false;
				}
			}
		}
	}

	private void addCustomerToSale(Scanner scanner) {
		System.out.println("\nTilføj Kunder...");
		System.out.print("Indtast Kundens telefonummer: ");
		String phoneNumber = scanner.next();
		Customer customer = controller.findCustomer(tlf);

		if (customer != null) {
			System.out.println("Kunden blev fundet: " + customer.getName() + " - " + customer.getEmail());
			controller.addCustomerToSale(customer);
		} else {
			System.out.println("Kunde med telefon nummer " + phoneNumber + " er ikke fundet.");
		}
	}

	}

	private void checkIfPaid() {
		System.out.println("\nBetaling....");
		if (controller.isPaid()) {
			System.out.println("Confirmed");
		} else {
			System.out.println("Afvist");
		}
	}

	public static void main(String[] args) {
		CreateInStoreSaleController controller = new CreateInStoreSaleController();
		InStoreSaleUI ui = new InStoreSaleUI(controller);
		ui.start();
	}
}

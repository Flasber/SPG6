package tui;

import model.BasicProduct;
import model.BillableItemContainer;
import model.BusinessCustomer;
import model.CompositeProduct;
import model.Customer;
import model.CustomerContainer;
import model.Employee;
import model.EmployeeContainer;
import model.Price;
import model.PrivateCustomer;
import model.WarrantyProduct;
import model.WarrantyProduct.Copy;

public class TryMe {
	EmployeeContainer employeeContainer;
	private CustomerContainer customerContainer;

	public static void main(String[] args) {
		TryMe tryMe = new TryMe();
		tryMe.generateTestData();
	}

	public void generateTestData() {

		generateEmployees();
		generateProducts();
		generateCustomers();
	}

	private void generateEmployees() {
		employeeContainer = EmployeeContainer.getinstance();
		Employee cashier1 = new Employee("Kasse-ekspedient 1", "kassemedarbejder", 0, 1);
		Employee ceo = new Employee("Anders", "leder", 0, 2);
		Employee manager1 = new Employee("Casper", "leder", 0, 3);
		Employee manager2 = new Employee("Thomas", "leder", 0, 4);
		Employee cashier2 = new Employee("Kasse-ekspedient 2", "kassemedarbejder", 0, 5);

		employeeContainer.addEmployee(cashier1);
		employeeContainer.addEmployee(ceo);
		employeeContainer.addEmployee(manager1);
		employeeContainer.addEmployee(manager2);
		employeeContainer.addEmployee(cashier2);

	}

	private void generateProducts() {
		WarrantyProduct p = new WarrantyProduct("et køleskab fra boch", "køleskab", new Price(100), "1.2.2.1", "98765",
				"000000001");
		WarrantyProduct s = new WarrantyProduct("en fryser fra samsung", "fryser", new Price(120), "1.2.2.2", "98764",
				"000000002");
		BasicProduct c = new BasicProduct("en hammer god til søm", "nail killer 3000", new Price(2), "1.1.1.1",
				"12345");
		BasicProduct l = new BasicProduct("en hammer beder til søm", "Nail killer 4000", new Price(5.5), "1.1.1.2",
				"12346");
		BasicProduct c1 = new BasicProduct("låge af slål", "Låge", new Price(5), "1.3.1.1", "11112");
		BasicProduct c2 = new BasicProduct("en ramme af Fyrtræ", "Ramme", new Price(50), "1.3.1.2", "11113");
		BasicProduct c3 = new BasicProduct("en hylle af bambus", "hylde", new Price(5), "1.3.1.3", "11114");
		CompositeProduct c10 = new CompositeProduct("skab i 7 dele", "skab", new Price(75), "to be done", "11115");
		c10.addCompositeLine(c1, 1);
		c10.addCompositeLine(c2, 1);
		c10.addCompositeLine(c3, 5);
		BasicProduct c4 = new BasicProduct("en borplade af marmor", "borplade", new Price(100), "1.3.1.4", "11116");
		BasicProduct c5 = new BasicProduct("en vask af cobber", "vask", new Price(50), "1.4.1.1", "11117");
		CompositeProduct c11 = new CompositeProduct("et komplet køkken", "køkken", new Price(250), "to be done",
				"11118");
		c10.addCompositeLine(c10, 2);
		c10.addCompositeLine(c4, 2);
		c10.addCompositeLine(c5, 1);
		// CompositeProduct cp1 = new CompositeProduct();

		Copy a = p.createCopy(202, "0000001", 5);
		Copy b = p.createCopy(301, "0000002", 2);
		Copy n = s.createCopy(230, "0000003", 4);
		Copy d = s.createCopy(991, "0000004", 1);

		BillableItemContainer billableItemContainer = BillableItemContainer.getInstance();
		billableItemContainer.addProduct(p);
		billableItemContainer.addProduct(s);
		billableItemContainer.addProduct(c);
		billableItemContainer.addProduct(l);
		billableItemContainer.addProduct(c1);
		billableItemContainer.addProduct(c2);
		billableItemContainer.addProduct(c3);
		billableItemContainer.addProduct(c4);
		billableItemContainer.addProduct(c5);
		billableItemContainer.addProduct(c10);
		billableItemContainer.addProduct(c11);

		Copy t = (Copy) billableItemContainer.findCopy("98765202");
		System.out.println(t.getWarranty());
	}

	private void generateCustomers() {
		CustomerContainer customerContainer = CustomerContainer.getInstance();
		Customer psycho = new PrivateCustomer("bob", "12345678", "a@b.com");
		Customer crazy8 = new PrivateCustomer("billy", "88888888", "b@a.com");
		Customer wack = new BusinessCustomer("billybob TømmerHandel", "87654321", "98765432", "ean1");
		customerContainer.addCustomer(psycho);
		customerContainer.addCustomer(wack);
		customerContainer.addCustomer(crazy8);
	}

}

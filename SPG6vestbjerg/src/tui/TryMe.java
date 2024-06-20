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

public class TryMe {
	public void generateTestData() {
		generateEmployees();
		generateProducts();
		generateCustomers();
	}

	private void generateEmployees() {
		EmployeeContainer employeeContainer = EmployeeContainer.getinstance();
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
		WarrantyProduct p = new WarrantyProduct("et køleskab fra bosch", "køleskab", new Price(100), "1.2.2.1", "98765",
				"000000001");
		WarrantyProduct s = new WarrantyProduct("en fryser fra samsung", "fryser", new Price(120), "1.2.2.2", "98764",
				"000000002");
		BasicProduct c = new BasicProduct("en hammer god til søm", "nail killer 3000", new Price(2), "1.1.1.1",
				"12345");
		c.addStockLocation(1000);
		BasicProduct l = new BasicProduct("en hammer bedre til søm", "Nail killer 4000", new Price(5.5), "1.1.1.2",
				"12346");
		l.addStockLocation(1000);
		BasicProduct c1 = new BasicProduct("låge af stål", "Låge", new Price(5), "1.3.1.1", "11112");
		c1.addStockLocation(1000);
		BasicProduct c2 = new BasicProduct("en ramme af fyrtræ", "Ramme", new Price(50), "1.3.1.2", "11113");
		c2.addStockLocation(1000);
		BasicProduct c3 = new BasicProduct("en hylde af bambus", "hylde", new Price(5), "1.3.1.3", "11114");
		c3.addStockLocation(1000);
		CompositeProduct c10 = new CompositeProduct("skab i 7 dele", "skab", new Price(75), "to be done", "11115");
		c10.addCompositeLine(c1, 1);
		c10.addCompositeLine(c2, 1);
		c10.addCompositeLine(c3, 5);
		BasicProduct c4 = new BasicProduct("en bordplade af marmor", "bordplade", new Price(100), "1.3.1.4", "11116");
		c4.addStockLocation(1000);
		BasicProduct c5 = new BasicProduct("en vask af kobber", "vask", new Price(50), "1.4.1.1", "11117");
		c5.addStockLocation(1000);
		CompositeProduct c11 = new CompositeProduct("et komplet køkken", "køkken", new Price(250), "to be done2",
				"11118");
		c11.addCompositeLine(c10, 2);
		c11.addCompositeLine(c4, 2);
		c11.addCompositeLine(c5, 1);

		p.createCopy(202, "0000001", 5);
		p.createCopy(301, "0000002", 2);
		s.createCopy(230, "0000003", 4);
		s.createCopy(991, "0000004", 1);

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
	}

	private void generateCustomers() {
		CustomerContainer customerContainer = CustomerContainer.getInstance();
		Customer bob = new PrivateCustomer("bob", "12345678", "a@b.com");
		Customer billy = new PrivateCustomer("billy", "88888888", "b@a.com");
		Customer billybob = new BusinessCustomer("billybob TømmerHandel", "87654321", "98765432", "ean1");
		customerContainer.addCustomer(bob);
		customerContainer.addCustomer(billybob);
		customerContainer.addCustomer(billy);
	}

}

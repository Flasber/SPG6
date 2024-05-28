package tui;

import model.BasicProduct;
import model.BillableItemContainer;
import model.BusinessCustomer;
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
		WarrantyProduct p = new WarrantyProduct("is milky", "milk", new Price(100), "56788", "12345", "yghjh");
		WarrantyProduct s = new WarrantyProduct("is nany", "banana", new Price(1.0), "56789", "22994", "ygjhjhjj");
		BasicProduct c = new BasicProduct("is cucumby", "cucumber", new Price(2), "56789", "22995");
		BasicProduct l = new BasicProduct("is milky", "milk", new Price(5.5), "56788", "32994");
		// CompositeProduct cp1 = new CompositeProduct();

		Copy a = p.createCopy(202, "pis", 5);
		Copy b = p.createCopy(301, "blah", 2);
		Copy n = s.createCopy(230, "blah", 4);
		Copy d = s.createCopy(991, "blah", 1);

		BillableItemContainer billableItemContainer = BillableItemContainer.getInstance();
		billableItemContainer.addProduct(p);
		billableItemContainer.addProduct(s);
		billableItemContainer.addProduct(c);
		billableItemContainer.addProduct(l);

		Copy t = billableItemContainer.findCopy("12345202");
		System.out.println(t.getWarranty());
	}

	private void generateCustomers() {
		CustomerContainer customerContainer = CustomerContainer.getInstance();
		Customer psycho = new PrivateCustomer("Crazy8", "12345678", "a@b.com");
		Customer crazy8 = new PrivateCustomer("13579", "Psycho", "55555555");
		Customer wack = new BusinessCustomer("67890", "Wack", "98765432", "ean1");
		customerContainer.addCustomer(psycho);
		customerContainer.addCustomer(wack);
		customerContainer.addCustomer(crazy8);
	}

}

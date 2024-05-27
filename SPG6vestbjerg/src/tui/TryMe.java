package tui;

import model.BasicProduct;
import model.BillableItemContainer;
import model.Customer;
import model.CustomerContainer;
import model.Employee;
import model.EmployeeContainer;
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
		WarrantyProduct p = new WarrantyProduct("is milky", "milk", "56788", "12345", "yghjh");
		WarrantyProduct s = new WarrantyProduct("is nany", "banana", "56789", "22994", "ygjhjhjj");
		BasicProduct c = new BasicProduct("is cucumby", "cucumber", "56789", "22995");
		BasicProduct l = new BasicProduct("is milky", "milk", "56788", "32994");

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

		Customer psycho = new Customer("12345", "Crazy8", "12345678");
		Customer wack = new Customer("67890", "Wack", "98765432");
		Customer crazy8 = new Customer("13579", "Psycho", "55555555");
		CustomerContainer customerContainer = CustomerContainer.getInstance();
		customerContainer.addCustomer(psycho);
		customerContainer.addCustomer(wack);
		customerContainer.addCustomer(crazy8);

	}

}

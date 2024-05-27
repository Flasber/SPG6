import controller.CreateInStoreSaleController;
import controller.EmployeeController;
import model.Employee;
import model.EmployeeContainer;
import model.Sale;

public class Program {

	public static void main(String[] args) throws Exception {
		// testFindEmployee();

		var con = new CreateInStoreSaleController();
		con.createInStoreSale(1, 1234);
		con.addItemToSale("1234-5678", 1);
		con.addItemToSale("1234-abcd", 5);
		con.addCustomerToSale("12345678");
		Sale sale = con.isPaid();
		for (var line : sale.getBillableLines()) {
			System.out.println(line);
		}
		System.out.println(sale.getTotal());

	}

	private static void testFindEmployee() {
		System.out.println("Testing Employee Controller with HashMap!");

		// Create an instance of EmployeeContainer
		EmployeeContainer employeeContainer = EmployeeContainer.getinstance();

		// Add some employees to the container
		employeeContainer.addEmployee(new Employee("Anders", "Developer", 40, 1)); // Employee with ID 1
		employeeContainer.addEmployee(new Employee("Charlie", "Manager", 45, 2)); // Employee with ID 2

		// Create an instance of EmployeeController
		EmployeeController employeeController = new EmployeeController();

		// Test finding employees by ID
		findAndPrintEmployee(employeeController, 1); // Search for employee with ID 1 (Anders)
		findAndPrintEmployee(employeeController, 2); // Search for employee with ID 2 (Charlie)
		findAndPrintEmployee(employeeController, 7); // Searching for a non-existent employee
	}

	// Helper method to find and print employee by ID
	private static void findAndPrintEmployee(EmployeeController employeeController, int employeeId) {
		Employee foundEmployee = employeeController.findEmployee(employeeId);
		if (foundEmployee != null) {
			System.out.println("Found Employee with ID " + employeeId + ": " + foundEmployee.getName());
		} else {
			System.out.println("Employee with ID " + employeeId + " not found.");
		}
	}
}

import model.Copy;

import controller.EmployeeController;
import model.Employee;
import model.EmployeeContainer;

public class Program {

    public static void main(String[] args) {
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

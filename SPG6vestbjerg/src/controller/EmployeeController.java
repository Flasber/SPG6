package controller;

import model.Employee;
import model.EmployeeContainer;

public class EmployeeController {

	public Employee findEmployee(int employeeId) {

		EmployeeContainer employeeContainer = EmployeeContainer.getinstance();

		return employeeContainer.findEmployee(employeeId);
	}

}

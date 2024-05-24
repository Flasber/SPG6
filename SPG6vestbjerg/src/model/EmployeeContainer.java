package model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeContainer {
	private static EmployeeContainer instance;
	private List<Employee> employees;

	private EmployeeContainer() {
		employees = new ArrayList<>();
	}

//singleton pattern.
	public static EmployeeContainer getinstance() {
		if (instance == null) {
			instance = new EmployeeContainer();
		}
		return instance;
	}

// to add a employee to the list

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
//find an employee with their employeeID

	public Employee findEmployee(int employeeId) {
		for (Employee employee : employees) {
			if (employee.getEmployeeId() == employeeId) {
				return employee;
			}
		}
		return null;
	}

}

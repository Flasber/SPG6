package model;

import java.util.HashMap;
import java.util.Map;

public class EmployeeContainer {
	private static EmployeeContainer instance;
	private Map<Integer, Employee> employees;

	private EmployeeContainer() {
		employees = new HashMap<>();
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
		employees.put(employee.getEmployeeId(), employee);
	}
//find an employee with their employeeID

	public Employee findEmployee(int employeeId) {
		return employees.get(employeeId);
	}
}

package model;

public interface Sale extends Billable {

	public void setCustomer(Customer c);

	public Customer getCustomer();

	public Employee getEmployee();

}

package controller;

import model.BillableContainer;
import model.BillableItem;
import model.Customer;
import model.Employee;
import model.InStoreSale;

public class CreateInStoreSaleController {

	private InStoreSale saleInProgress;
	private InStoreSale lastSale;

	public void createInStoreSale(int registerNo, int employeeId) {
		EmployeeController ectrl = new EmployeeController();
		Employee e = ectrl.findEmployee(employeeId);
		if (e == null) {
			throw new IllegalArgumentException("no employee has that id");
		}
		saleInProgress = new InStoreSale(registerNo, e);
	}

	public BillableItem addItemToSale(String barcode, int quantity) throws Exception {
		BillableItemController bictrl = new BillableItemController();
		BillableItem bi = bictrl.findItem(barcode);
		if (bi != null) {
			saleInProgress.addItem(bi, quantity);
		}
		return bi;
	}

	public Customer addCustomerToSale(String tlf) {
		CustomerController cctrl = new CustomerController();
		Customer c = cctrl.findCustomer(tlf);
		if (c != null) {
			saleInProgress.setCustomer(c);
		}
		return c;

	}

	// Signal to the controller that the saleInProgress has been paid for,
	// completing the sale.
	public InStoreSale isPaid() {
		BillableContainer bc = BillableContainer.getInstance();
		bc.addSale(saleInProgress);
		lastSale = saleInProgress;
		saleInProgress = null;
		return lastSale;
	}

	public InStoreSale getLastSale() {
		return lastSale;
	}

	public InStoreSale getSaleinProgress() {
		return saleInProgress;
	}

}

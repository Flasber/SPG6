package controller;

import model.BillableContainer;
import model.Copy;
import model.Customer;
import model.Employee;
import model.InStoreSale;
import model.Product;

public class CreateInStoreSaleController {

	private InStoreSale saleInProgress;
	private Product scannedItem;
	private Copy scannedCopy;

	public void createInStoreSale(int registerNo, int employeeId) {
		EmployeeController ectrl = new EmployeeController();
		Employee e = ectrl.findEmployee(employeeId);
		saleInProgress = new InStoreSale(registerNo, e);
	}

	public void addProductToSale(String barcode, int quantity) {
		ProductController pctrl = new ProductController();
		String copyId = pctrl.copyIdFromBarcode(barcode);
		if (copyId == null) {
			Product p = pctrl.findProduct(barcode);
			saleInProgress.addProduct(p, quantity);
		} else {
			Copy c = pctrl.findCopy(barcode);
			saleInProgress.addCopy(c);
		}

	}

	public boolean addCustomerToSale(String tlf) {
		CustomerController cctrl = new CustomerController();
		Customer c = cctrl.findCustomer(tlf);
		if (c == null) {
			return false;
		} else {
			saleInProgress.setCustomer(c);
			return true;
		}

	}

	public InStoreSale isPaid() {
		BillableContainer bc = BillableContainer.getInstance();
		if (bc.add(saleInProgress)) {
			return saleInProgress;
		} else
			return null;

	}

}

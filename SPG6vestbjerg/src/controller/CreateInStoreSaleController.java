package controller;

import java.util.ArrayList;

import model.BillableContainer;
import model.BillableItem;
import model.BillableLine;
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
	public InStoreSale isPaid() throws Exception {
		BillableContainer bc = BillableContainer.getInstance();
		bc.addSale(saleInProgress);
		updateStockArfterSale();
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

	public void updateStockArfterSale() throws Exception {
		if (saleInProgress != null) {
			ArrayList<BillableLine> billableLine = saleInProgress.getBillableLines();
			for (int i = 0; i < billableLine.size(); i++) {
				{
					BillableLine b = billableLine.get(i);
					BillableItem p = b.getItem();
					int a = b.getQuantity();
					p.removestock(a);

				}
			}
		}

	}
}

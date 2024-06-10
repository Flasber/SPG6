package controller;

import model.BillableContainer;
import model.BillableItem;
import model.BillableLine;
import model.Customer;
import model.Employee;
import model.InStoreSale;

public class CreateInStoreSaleController {

	private InStoreSale saleInProgress;
	private InStoreSale lastSale;

	/**
	 * Creates an in-store sale with the given register number and employee ID.
	 * 
	 * @param registerNo The register number for the sale.
	 * @param employeeId The ID of the employee processing the sale.
	 */

	public void createInStoreSale(int registerNo, int employeeId) {
		EmployeeController ectrl = new EmployeeController();
		Employee e = ectrl.findEmployee(employeeId);
		if (e == null) {
			throw new IllegalArgumentException("no employee has that id");
		}
		saleInProgress = new InStoreSale(registerNo, e);
	}

	/**
	 * Adds an item with the given barcode and quantity to the sale in progress.
	 * 
	 * @param barcode  The barcode of the item to be added.
	 * @param quantity The quantity of the item to be added.
	 * @return The added billable item.
	 * @throws Exception If an error occurs while adding the item.
	 */

	public BillableItem addItemToSale(String barcode, int quantity) throws Exception {
		BillableItemController bictrl = new BillableItemController();
		BillableItem bi = bictrl.findItem(barcode);
		if (bi != null) {
			saleInProgress.addItem(bi, quantity);
		}
		return bi;
	}

	/**
	 * Adds a customer with the given phone number to the sale in progress.
	 * 
	 * @param tlf = the phonenumber of the customer to be added
	 * @return The added customer
	 */

	public Customer addCustomerToSale(String tlf) {
		CustomerController cctrl = new CustomerController();
		Customer c = cctrl.findCustomer(tlf);
		if (c != null) {
			saleInProgress.setCustomer(c);
		}
		return c;

	}

	/**
	 * Signal to the controller that the saleInProgress has been paid for,
	 * completing the sale.
	 * 
	 * @return last sale
	 * @throws Exception if an error occurs
	 */

	public InStoreSale isPaid() throws Exception {
		BillableContainer bc = BillableContainer.getInstance();
		bc.addSale(saleInProgress);
		updateStockAfterSale();
		lastSale = saleInProgress;
		saleInProgress = null;
		return lastSale;
	}

	/**
	 * Retrieves the last completed sale.
	 * 
	 * @return The last completed sale.
	 */
	public InStoreSale getSaleInProgress() {
		return lastSale;
	}

	/**
	 * Retrieves the sale in progress.
	 * 
	 * @return The sale in progress.
	 */

	public InStoreSale getSaleinProgress() {
		return saleInProgress;
	}

	/**
	 * Updates the stock levels after completing the sale.
	 * 
	 * @throws Exception If an error occurs while updating the stock levels.
	 */

	public void updateStockAfterSale() throws Exception {
		if (saleInProgress != null) {
			BillableItemController bictrl = new BillableItemController();
			for (BillableLine line : saleInProgress.getBillableLines()) {
				bictrl.removeStock(line.getItem(), line.getQuantity());
			}
		}
	}
}

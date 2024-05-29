
package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InStoreSale implements Billable, Sale {
	private int registerNo;
	private Employee employee;
	private Customer customer;
	private LocalDateTime createdDate;
	private List<BillableLine> orderLines;
	private int id;

	public InStoreSale(int registerNo, Employee e) {
		this.registerNo = registerNo;
		this.employee = e;
		orderLines = new ArrayList<>();
		createdDate = LocalDateTime.now();
	}

	public void setCustomer(Customer c) {
		this.customer = c;
	}

	public class AddWarrantyProductException extends Exception {
		private static final long serialVersionUID = 7617915006698276761L;
	}

	public class UnexpectedClassException extends Exception {
		private static final long serialVersionUID = 7194603038292464598L;
	}

	public void addItem(BillableItem i, int q) throws Exception {
		if (i instanceof WarrantyProduct.Copy) {
			if (q != 1) {
				throw new IllegalArgumentException("can't have more than 1 in a WarrantyBillableLine");
			}
			addCopy((WarrantyProduct.Copy) i);
		} else if (i instanceof NonWarrantyProduct) {
			addProduct((NonWarrantyProduct) i, q);
		} else if (i instanceof WarrantyProduct) {
			throw new AddWarrantyProductException();
		} else {
			throw new UnexpectedClassException();
		}
	}

	public void addItem(BillableItem i) throws Exception {
		addItem(i, 1);
	}

	private void addProduct(NonWarrantyProduct p, int qu) throws Exception {
		Iterator<BillableLine> it = orderLines.iterator();
		boolean isFound = false;
		NormalBillableLine bol = null;
		while (it.hasNext() && !isFound) {
			BillableLine ol = it.next();
			if (ol instanceof NormalBillableLine) {
				NonWarrantyProduct product = ((NormalBillableLine) ol).getProduct();
				if (product == p) {
					isFound = true;
					bol = ((NormalBillableLine) ol);
				}

			}

		}
		if (bol != null) {
			bol.increaseQuantity(qu);
		} else {
			orderLines.add(new NormalBillableLine(qu, p));
		}
	}

	public void addProduct(NonWarrantyProduct p) throws Exception {
		addProduct(p, 1);
	}

	private void addCopy(WarrantyProduct.Copy c) throws Exception {
		Iterator<BillableLine> it = orderLines.iterator();
		boolean isFound = false;
		WarrantyBillableLine col = null;
		while (it.hasNext() && !isFound) {
			BillableLine ol = it.next();
			if (ol instanceof WarrantyBillableLine) {
				WarrantyProduct.Copy copy = ((WarrantyBillableLine) ol).getItem();
				if (copy == c) {
					isFound = true;
					col = ((WarrantyBillableLine) ol);
				}

			}

		}
		if (col != null) {
			throw new Exception();
		} else {
			orderLines.add(new WarrantyBillableLine(c));
		}
	}

	@Override
	public ArrayList<BillableLine> getBillableLines() {
		return new ArrayList<>(orderLines);
	}

	@Override
	public BigDecimal getTotal() {

		BigDecimal sum = new BigDecimal(0);
		for (int i = 0; i < orderLines.size(); i++) {
			BillableLine b = orderLines.get(i);
			sum = sum.add(b.getSubTotal());
		}
		return sum;
	}

	@Override
	public LocalDateTime getCreateDate() {
		return createdDate;
	}

	@Override
	public int getBillableId() {
		return id;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public Employee getEmployee() {
		return employee;
	}

	public int getRegisterNo() {

		return registerNo;
	}
}
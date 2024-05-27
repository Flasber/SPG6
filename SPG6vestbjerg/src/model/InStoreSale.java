package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InStoreSale {
	private int registerNo;
	private Employee e;
	private Customer c;
	private LocalDate createdDate;
	private List<OrderLine> orderLines;

	public InStoreSale(int registerNo, Employee e) {
		this.registerNo = registerNo;
		this.e = e;
		orderLines = new ArrayList<>();
		createdDate = LocalDate.now();
	}

	public void setCustomer(Customer c) {
		this.c = c;
	}

	public void addItem(BillableItem i) throws Exception {
		if (i instanceof WarrantyProduct.Copy) {
			addCopy((WarrantyProduct.Copy) i);

		} else if (i instanceof NonWarrantyProduct) {
			addProduct((Product) i);
		} else {
			throw new Exception();
		}
	}

	private void addProduct(Product p, int qu) {
		Iterator<OrderLine> it = orderLines.iterator();
		boolean isFound = false;
		NormalBillableLine bol = null;
		while (it.hasNext() && !isFound) {
			OrderLine ol = it.next();
			if (it.next() instanceof NormalBillableLine) {
				Product product = ((NormalBillableLine) ol).getProduct();
				if (product == p) {
					isFound = true;
					bol = ((NormalBillableLine) ol);
				}

			}

		}
		if (pol != null) {
			pol.increaseQuantity(qu);
		} else {
			orderLines.add(new NormalBillableLine(qu, p));
		}
	}

	public void addProduct(Product p) {
		addProduct(p, 1);
	}

	private void addCopy(WarrantyProduct.Copy c) throws Exception {
		Iterator<OrderLine> it = orderLines.iterator();
		boolean isFound = false;
		CopyOrderLine col = null;
		while (it.hasNext() && !isFound) {
			OrderLine ol = it.next();
			if (it.next() instanceof CopyOrderLine) {
				WarrantyProduct.Copy copy = ((CopyOrderLine) ol).getCopy();
				if (copy == c) {
					isFound = true;
					col = ((CopyOrderLine) ol);
				}

			}

		}
		if (col != null) {
			throw new Exception();
		} else {
			orderLines.add(new CopyOrderLine(c));
		}
	}

	public List<OrderLine> getProducts;
//start From here
}

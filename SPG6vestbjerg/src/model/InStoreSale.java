package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InStoreSale {
	private int registerNo;
	private Employee e;
	private Customer c;
	private LocalDate createdDate;
	private List<BillableLine> orderLines;

	public InStoreSale(int registerNo, Employee e) {
		this.registerNo = registerNo;
		this.e = e;
		orderLines = new ArrayList<>();
		createdDate = LocalDate.now();
	}

	public void setCustomer(Customer c) {
		this.c = c;
	}

	public void addItem(BillableItem i, int q) throws Exception {
		if

		(i instanceof WarrantyProduct.Copy) {
			if (q != 1) {
				throw new Exception();
			}
			addCopy((WarrantyProduct.Copy) i);

		} else if (i instanceof NonWarrantyProduct) {
			addProduct((Product) i, q);
		} else {
			throw new Exception();
		}
	}

	private void addProduct(Product p, int qu) {
		Iterator<BillableLine> it = orderLines.iterator();
		boolean isFound = false;
		NormalBillableLine bol = null;
		while (it.hasNext() && !isFound) {
			BillableLine ol = it.next();
			if (it.next() instanceof NormalBillableLine) {
				Product product = ((NormalBillableLine) ol).getProduct();
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

	public void addProduct(Product p) {
		addProduct(p, 1);
	}

	private void addCopy(WarrantyProduct.Copy c) throws Exception {
		Iterator<BillableLine> it = orderLines.iterator();
		boolean isFound = false;
		WarrantyBillableLine col = null;
		while (it.hasNext() && !isFound) {
			BillableLine ol = it.next();
			if (it.next() instanceof WarrantyBillableLine) {
				WarrantyProduct.Copy copy = ((WarrantyBillableLine) ol).getCopy();
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

	public List<BillableLine> getBillableLines() {
		return new ArrayList<BillableLine>(orderLines);
	}

	public BigDecimal getTotal() {

		BigDecimal sum = new BigDecimal(0);
		for (int i = 0; i < orderLines.size(); i++) {
			BillableLine b = orderLines.get(i);
			sum = sum.add(b.getSubTotal());
		}
		return sum;
	}

//start From here
}
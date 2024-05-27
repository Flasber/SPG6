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

	public void addProduct(Product p, int qu) {
		Iterator<OrderLine> it = orderLines.iterator();
		boolean isFound = false;
		ProductOrderLine pol = null;
		while (it.hasNext() && !isFound) {
			OrderLine ol = it.next();
			if (it.next() instanceof ProductOrderLine) {
				Product product = ((ProductOrderLine) ol).getProduct();
				if (product == p) {
					isFound = true;
					pol = ((ProductOrderLine) ol);
				}

			}

		}
		if (pol != null) {
			pol.increaseQuantity(qu);
		} else {
			orderLines.add(new ProductOrderLine(qu, p));
		}
	}

	public void addProduct(Product p) {
		addProduct(p, 1);
	}

	public void addCopy(WarrantyProduct.Copy c) throws Exception {
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



}

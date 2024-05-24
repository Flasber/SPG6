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

	public void addProduct(Product p) {
		Iterator<OrderLine> it = orderLines.iterator();
		while (it.hasNext() && true) {
			OrderLine ol = it.next();
			if (it.next() instanceof ProductOrderLine) {

			}
			ol.getProduct();

		}

	}

}

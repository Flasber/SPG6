/**
 * 
 */
package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.BasicProduct;
import model.BillableItemContainer;
import model.BillableLine;
import model.CompositeProduct;
import model.Customer;
import model.CustomerContainer;
import model.Employee;
import model.EmployeeContainer;
import model.InStoreSale;
import model.Price;
import model.PrivateCustomer;
import model.Sale;
import model.WarrantyProduct;
import model.WarrantyProduct.Copy;

/**
 * 
 */
class CreateInStoreSaleControllerTest {
	private CreateInStoreSaleController ctrl;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	public static void initialization() throws Exception {
		EmployeeContainer employeeContainer = EmployeeContainer.getinstance();
		Employee cashier1 = new Employee("Kasse-ekspedient 1", "kassemedarbejder", 0, 1);
		Employee cashier2 = new Employee("Kasse-ekspedient 2", "kassemedarbejder", 0, 5);
		employeeContainer.addEmployee(cashier1);
		employeeContainer.addEmployee(cashier2);
		WarrantyProduct p = new WarrantyProduct("et køleskab fra boch", "køleskab", new Price(100), "1.2.2.1", "98765",
				"000000001");
		WarrantyProduct s = new WarrantyProduct("en fryser fra samsung", "fryser", new Price(120), "1.2.2.2", "98764",
				"000000002");
		BasicProduct c = new BasicProduct("en hammer god til søm", "nail killer 3000", new Price(2), "1.1.1.1",
				"12345");
		BasicProduct l = new BasicProduct("en hammer beder til søm", "Nail killer 4000", new Price(5.5), "1.1.1.2",
				"12346");
		BasicProduct c1 = new BasicProduct("låge af slål", "Låge", new Price(5), "1.3.1.1", "11112");
		BasicProduct c2 = new BasicProduct("en ramme af Fyrtræ", "Ramme", new Price(50), "1.3.1.2", "11113");
		BasicProduct c3 = new BasicProduct("en hylle af bambus", "hylde", new Price(5), "1.3.1.3", "11114");
		CompositeProduct c10 = new CompositeProduct("skab i 7 dele", "skab", new Price(75), "to be done", "11115");
		c10.addCompositeLine(c1, 1);
		c10.addCompositeLine(c2, 1);
		c10.addCompositeLine(c3, 5);
		Copy a = p.createCopy(202, "0000001", 5);
		Copy b = p.createCopy(301, "0000002", 2);
		Copy n = s.createCopy(230, "0000003", 4);
		Copy d = s.createCopy(991, "0000004", 1);

		BillableItemContainer billableItemContainer = BillableItemContainer.getInstance();
		billableItemContainer.addProduct(p);
		billableItemContainer.addProduct(s);
		billableItemContainer.addProduct(c);
		billableItemContainer.addProduct(l);
		billableItemContainer.addProduct(c1);
		billableItemContainer.addProduct(c2);
		billableItemContainer.addProduct(c3);
		CustomerContainer customerContainer = CustomerContainer.getInstance();
		Customer psycho = new PrivateCustomer("bob", "12345678", "a@b.com");
		customerContainer.addCustomer(psycho);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		ctrl = new CreateInStoreSaleController();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testCreateInStoreSale() {
		ctrl.createInStoreSale(1, 5);
		InStoreSale sale = ctrl.getSaleinProgress();
		assertNotNull(sale);
		assertEquals(1, sale.getRegisterNo());
		assertEquals(5, sale.getEmployee().getEmployeeId());
	}

	@Test
	public void testAddItemToSale() throws Exception {
		ctrl.createInStoreSale(1, 5);
		InStoreSale sale = ctrl.getSaleinProgress();
		ctrl.addItemToSale("12345", 2);
		Sale s = ctrl.isPaid();
		ArrayList<BillableLine> b = sale.getBillableLines();
		assertEquals(1, b.size());
		assertEquals(2, b.get(0).getQuantity());
		assertEquals("12345", b.get(0).getItem().getBarcode());
	}

	@Test
	public void testAddCustomerToSale() {
		ctrl.createInStoreSale(1, 5);
		InStoreSale sale = ctrl.getSaleinProgress();
		Customer customer = ctrl.addCustomerToSale("12345678");
		assertNotNull(sale.getCustomer());
		assertEquals("12345678", sale.getCustomer().getTlf());

	}

	@Test
	public void testIsPaid() throws Exception {
		ctrl.createInStoreSale(1, 5);
		InStoreSale sale = ctrl.isPaid();
		assertNotNull(sale);
		assertEquals(sale, ctrl.getLastSale());
	}

}

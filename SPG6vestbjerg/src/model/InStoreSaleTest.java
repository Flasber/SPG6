package model;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InStoreSaleTest {
	private class TryMe {
		public static Copy c1;
		public static Copy c2;
		public static Product p1;
		public static Product p2;
		public static Employee e1;

		public static void makeData() {
			c1 = new Copy(1, "test", 99);
			c2 = new Copy(2, "test2", 2);
			p1 = new Product("kills Nails", "hammer", "1.1.1.1", "123456789");
			p2 = new Product("kills more Nails", "better hammer", "1.1.1.2", "987654321");
			e1 = new Employee("billybob", "job", 20, 11);
		}
	}

	@BeforeAll
	static void setUpBeforeClass() {
		TryMe.makeData();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	private InStoreSale s1;

	@BeforeEach
	void setUp() throws Exception {
		s1 = new InStoreSale(1, TryMe.e1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInStoreSale_works_whenCalledCorectly() throws Exception {
		s1.addCopy(TryMe.c1);
		s1.addCopy(TryMe.c2);
		s1.addProduct(TryMe.p1);
		s1.addProduct(TryMe.p2);
	}

	@Test
	void testSetCustomer() {
		// start from Here

	}

	@Test
	void testAddProductProductInt() {
		fail("Not yet implemented");
	}

	@Test
	void testAddProductProduct() {
		fail("Not yet implemented");
	}

	@Test
	void testAddCopy() {
		fail("Not yet implemented");
	}

}

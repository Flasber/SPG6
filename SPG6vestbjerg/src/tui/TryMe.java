package tui;
import model.BasicProduct;
import model.BillableItemContainer;
import model.WarrantyProduct;

public class TryMe {

	public static void main(String[] args) {
		WarrantyProduct p = new WarrantyProduct("is milky", "milk", "56788", "12345", "yghjh");
		WarrantyProduct s = new WarrantyProduct("is nany", "banan", "56788", "22994", "ygjhjhjj");
		BasicProduct c = new BasicProduct("is cucumby", "cucumber", "56789", "22995");
		BasicProduct l = new BasicProduct("is milky", "milk", "56788", "32994");
		WarrantyProduct.Copy a = p.createCopy(202, "pis", 5);
		WarrantyProduct.Copy b = p.createCopy(301, "blah", 2);
		WarrantyProduct.Copy n = s.createCopy(230, "blah", 4);
		WarrantyProduct.Copy d = s.createCopy(991, "blah", 1);
		BillableItemContainer pc = BillableItemContainer.getInstance();
		pc.addProduct(p);
		pc.addProduct(s);
		pc.addProduct(c);
		pc.addProduct(l);
		WarrantyProduct.Copy t = pc.findCopy("12345202");
		System.out.println(t.getWarranty());

	}

}
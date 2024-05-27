import controller.ProductController;
import model.BasicProduct;
import model.Copy;
import model.Product;
import model.ProductContainer;
import model.WarrantyProduct;

public class TryMe {

	public static void main(String[] args) {
		Product p = new WarrantyProduct("is milky", "milk", "56788", "12345", "yghjh");
		Product s = new WarrantyProduct("is nany", "banan", "56788", "22994", "ygjhjhjj");
		Product c = new BasicProduct("is cucumby", "cucumber", "56789", "22995");
		Product l = new BasicProduct("is milky", "milk", "56788", "32994");
		Copy a = new Copy(202, "pis", 5);
		Copy b = new Copy(301, "blah", 2);
		Copy n = new Copy(230, "blah", 4);
		Copy d = new Copy(991, "blah", 1);
		// Copy e=new Copy(555, "blah", 4);
		((WarrantyProduct) p).addCopy(a);
		((WarrantyProduct) p).addCopy(b);
		((WarrantyProduct) s).addCopy(n);
		((WarrantyProduct) s).addCopy(d);
		ProductContainer pc = ProductContainer.getInstance();
		pc.addProduct(p);
		pc.addProduct(s);
		pc.addProduct(c);
		pc.addProduct(l);
		ProductController pct = new ProductController();
		Copy t = pct.findCopy("12345202");
		System.out.println(t.getWarranty());

	}

}
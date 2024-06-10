import controller.CreateInStoreSaleController;
import gui.InStoreSaleGUI;
import tui.TryMe;

public class Program {
	public static void main(String[] args) {
		TryMe tryMe = new TryMe();
		tryMe.generateTestData();
		CreateInStoreSaleController controller = new CreateInStoreSaleController();
		new InStoreSaleGUI(controller);
	}
}

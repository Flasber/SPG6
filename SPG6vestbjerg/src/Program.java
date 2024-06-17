import gui.MainMenu;
import tui.TryMe;

import java.awt.EventQueue;

public class Program {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
					TryMe tryMe = new TryMe();
					tryMe.generateTestData();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

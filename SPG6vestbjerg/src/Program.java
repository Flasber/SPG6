import tui.InStoreSaleUI;

public class Program {
	/**
	 * Run the InStoreSaleUI, which currently implements the 'Sell in store'
	 * usecase.
	 * Suggested inputs:
	 * Kassenummer: 1
	 * Medarbejder Nummer: 2
	 * Stregkode og antal: 
	 * 		12345 * 5
	 * 		98765 * 1 (alt andet end * 1 fejler)
	 * Quit by answering 'nej'.
	 */
	public static void main(String[] args) throws Exception {
		InStoreSaleUI.start();
	}
}

import java.util.List;

import model.Bill;
import model.CompositeProductDescription;
import model.FungibleProductDescription;
import model.RentalDescription;
import model.RentalProductDescription;
import model.WarrantyDescription;
import model.WarrantyProductDescription;

public class Program {
	public static void main(String[] args) {
		var bandaid = new FungibleProductDescription("bandaid");
		var hammer = new FungibleProductDescription("hammer");
		var nails = new FungibleProductDescription("nails");

		var firstAidKit = new CompositeProductDescription("first aid kit", List.of(bandaid, bandaid, bandaid));

		var nailingComboPack = new CompositeProductDescription("nailing combo pack",
				List.of(hammer, nails, nails, firstAidKit));

		var cementMixerDesc = new RentalProductDescription("cement mixer");
		cementMixerDesc.createItem();
		cementMixerDesc.createItem();

		var rentACementMixer = new RentalDescription("rent-a-cement-mixer", cementMixerDesc);
		var aParticularRental = rentACementMixer.toBillable();

		var oneWeekWarrantyDesc = new WarrantyDescription("one week");
		var fridge = new WarrantyProductDescription("fridge", oneWeekWarrantyDesc);
		var aParticularFridge = fridge.createItem();

		var kitchenComboPack = new CompositeProductDescription("DIY kitchen combo pack",
				List.of(nailingComboPack, nailingComboPack));

//		var bill = new VisitorBill();
//		bill.add(bandaid);
//		bill.add(hammer, 2);
//		bill.add(nails, 10);
//		bill.add(aParticularRental);
//		bill.add(aParticularFridge);
//		bill.add(nailingComboPack, 2);
//		bill.add(rentACementMixer);
//		bill.add(kitchenComboPack);
		var bill = new Bill();
		bill.addBillable(bandaid);
		bill.addBillable(hammer, 2);
		bill.addBillable(nails, 10);
		bill.addBillable(aParticularRental);
		bill.addBillable(aParticularFridge);
		bill.addCompositeDescription(nailingComboPack, 2);
		bill.addBillableDescription(rentACementMixer);
		bill.addCompositeDescription(kitchenComboPack);

		System.out.println(bill.formatReceipt());
	}
}

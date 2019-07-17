package amata1219.amalib.inventory.ui.option;

public enum InventoryLine {

	x1,
	x2,
	x3,
	x4,
	x5,
	x6;

	public int inventorySize(){
		return (ordinal() + 1) * 9;
	}

}

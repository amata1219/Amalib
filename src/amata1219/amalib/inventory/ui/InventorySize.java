package amata1219.amalib.inventory.ui;

public enum InventorySize {

	ONE,
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX;

	public int size(){
		return (ordinal() + 1) * 9;
	}

}

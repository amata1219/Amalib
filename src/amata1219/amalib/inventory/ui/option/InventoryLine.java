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

	public static InventoryLine necessaryInventoryLine(int size){
		switch((size - 1) / 9){
		case 0:
			return InventoryLine.x1;
		case 1:
			return InventoryLine.x2;
		case 2:
			return InventoryLine.x3;
		case 3:
			return InventoryLine.x4;
		case 4:
			return InventoryLine.x5;
		default:
			return InventoryLine.x6;
		}
	}

}

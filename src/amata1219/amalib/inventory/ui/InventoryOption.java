package amata1219.amalib.inventory.ui;

import org.bukkit.event.inventory.InventoryType;

public class InventoryOption {

	public final int size;
	public final InventoryType type;

	public InventoryOption(InventorySize size){
		this(size.size(), null);
	}

	public InventoryOption(InventoryType type){
		this(InventorySize.ONE.size(), type);
	}

	private InventoryOption(int size, InventoryType type){
		this.size = size;
		this.type = type;
	}

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


}

package amata1219.amalib.inventory.ui;

import org.bukkit.event.inventory.InventoryType;

public class InventoryInformation {

	public final int size;
	public final InventoryType type;

	public InventoryInformation(InventorySize size){
		this(size.size(), null);
	}

	public InventoryInformation(InventoryType type){
		this(InventorySize.ONE.size(), type);
	}

	private InventoryInformation(int size, InventoryType type){
		this.size = size;
		this.type = type;
	}

}

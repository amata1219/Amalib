package amata1219.amalib.inventory.ui;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryLayout {

	public String title;
	public final InventoryUI ui;
	public final InventoryInformation information;
	private final HashMap<Integer, Slot> slots = new HashMap<>();
	private Applier<Slot> defaultSlotApplier;
	private Applier<InventoryOpenEvent> actionOnOpen;
	private Applier<InventoryCloseEvent> actionOnClose;

	public InventoryLayout(InventoryUI ui, InventoryInformation information){
		this.ui = ui;
		this.information = information;
	}

	public Inventory buildInventory(){
		Inventory inventory = null;

		int size = information.size;
		InventoryType type = information.type;

		if(type != null)
			if(title != null)
				inventory = Bukkit.createInventory(ui, size, title);
			else
				inventory = Bukkit.createInventory(ui, size);
		else
			if(title != null)
				inventory = Bukkit.createInventory(ui, type, title);
			else
				inventory = Bukkit.createInventory(ui, type);

		for(int index = 0; index < size; index++)
			inventory.setItem(index, getSlotAt(index));
	}

	public Slot getSlotAt(int index){
		return slots.containsKey(index) ? slots.get(index) : defaultSlotApplier.apply(new Slot());
	}

	public void fire(InventoryOpenEvent event){
		if(actionOnOpen != null)
			actionOnOpen.action(event);
	}

	public void fire(InventoryCloseEvent event){
		if(actionOnClose != null)
			actionOnClose.action(event);
	}

}

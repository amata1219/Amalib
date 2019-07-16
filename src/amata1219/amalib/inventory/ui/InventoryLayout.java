package amata1219.amalib.inventory.ui;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InventoryLayout {

	private final InventoryUI ui;
	private final InventoryOption option;

	public String title;
	private final HashMap<Integer, Slot> slots = new HashMap<>();
	private Applicator<Slot> defaultSlotApplicator;
	private Consumer<InventoryOpenEvent> actionOnOpen;
	private Consumer<InventoryCloseEvent> actionOnClose;

	public InventoryLayout(InventoryUI ui, InventoryOption option){
		this.ui = ui;
		this.option = option;
	}

	public Slot getSlotAt(int slotIndex){
		return slots.containsKey(slotIndex) ? slots.get(slotIndex) : defaultSlotApplicator.applicate(new Slot());
	}

	public Inventory buildInventory(){
		Inventory inventory = createInventory(ui, option, title);

		for(int slotIndex = 0; slotIndex < inventory.getSize(); slotIndex++)
			inventory.setItem(slotIndex, getSlotAt(slotIndex).buildIcon().toItemStack());

		return inventory;
	}

	public void defaultSlot(Applicator<Slot> applicator){
		defaultSlotApplicator = applicator;
	}

	public void put(Applicator<Slot> slotApplicate, int... slotIndexes){
		for(int slotIndex : slotIndexes)
			slots.put(slotIndex, slotApplicate.applicate(new Slot()));
	}

	public void put(Applicator<Slot> slotApplicate, IntStream range){
		put(slotApplicate, range.toArray());
	}

	public void onOpen(Consumer<InventoryOpenEvent> action){
		actionOnOpen = action;
	}

	public void onClose(Consumer<InventoryCloseEvent> action){
		actionOnClose = action;
	}

	public void fire(InventoryOpenEvent event){
		actionOnOpen.accept(event);
	}

	public void fire(InventoryCloseEvent event){
		actionOnClose.accept(event);
	}

	private Inventory createInventory(InventoryHolder holder, InventoryOption option, String title){
		int size = option.size;
		InventoryType type = option.type;
		if(option.type != null)
			if(title != null)
				return Bukkit.createInventory(holder, type, title);
			else
				return Bukkit.createInventory(holder, type);
		else
			if(title != null)
				return Bukkit.createInventory(holder, size, title);
			else
				return Bukkit.createInventory(holder, size);
	}

}

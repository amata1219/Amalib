package amata1219.amalib.inventory.ui.dsl.component;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import amata1219.amalib.inventory.ui.Applier;
import amata1219.amalib.inventory.ui.dsl.InventoryUI;
import amata1219.amalib.inventory.ui.dsl.event.InventoryUICloseEvent;
import amata1219.amalib.inventory.ui.dsl.event.InventoryUIOpenEvent;
import amata1219.amalib.inventory.ui.option.InventoryOption;

public class InventoryLayout {

	public final Player player;
	public final InventoryUI ui;
	public final InventoryOption option;

	public String title;
	private final HashMap<Integer, Slot> slots = new HashMap<>();
	private Applier<Slot> defaultSlotApplier;
	private Consumer<InventoryUIOpenEvent> actionOnOpen;
	private Consumer<InventoryUICloseEvent> actionOnClose;

	public InventoryLayout(Player player, InventoryUI ui, InventoryOption option){
		this.player = player;
		this.ui = ui;
		this.option = option;
	}

	public Slot getSlotAt(int slotIndex){
		return slots.containsKey(slotIndex) ? slots.get(slotIndex) : defaultSlotApplier.apply(new Slot());
	}

	public Inventory buildInventory(){
		Inventory inventory = createInventory(ui, option, title);

		for(int slotIndex = 0; slotIndex < inventory.getSize(); slotIndex++)
			inventory.setItem(slotIndex, getSlotAt(slotIndex).buildIcon().toItemStack());

		return inventory;
	}

	public void defaultSlot(Applier<Slot> applier){
		defaultSlotApplier = applier;
	}

	public void put(Applier<Slot> slotApplier, int... slotIndexes){
		for(int slotIndex : slotIndexes)
			slots.put(slotIndex, slotApplier.apply(new Slot()));
	}

	public void put(Applier<Slot> slotApplicate, IntStream range){
		put(slotApplicate, range.toArray());
	}

	public void onOpen(Consumer<InventoryUIOpenEvent> action){
		actionOnOpen = action;
	}

	public void onClose(Consumer<InventoryUICloseEvent> action){
		actionOnClose = action;
	}

	public void fire(InventoryOpenEvent event){
		actionOnOpen.accept(new InventoryUIOpenEvent(event));
	}

	public void fire(InventoryCloseEvent event){
		actionOnClose.accept(new InventoryUICloseEvent(event));
	}

	private Inventory createInventory(InventoryHolder holder, InventoryOption option, String title){
		int size = option.size;
		InventoryType type = option.type;

		if(option.type == null)
			if(title != null)
				return Bukkit.createInventory(holder, size, title);
			else
				return Bukkit.createInventory(holder, size);
		else
			if(title != null)
				return Bukkit.createInventory(holder, type, title);
			else
				return Bukkit.createInventory(holder, type);
	}

}

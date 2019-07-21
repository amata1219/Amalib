package amata1219.amalib.inventory.ui.dsl.component;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import amata1219.amalib.inventory.ui.Applier;
import amata1219.amalib.inventory.ui.dsl.InventoryUI;
import amata1219.amalib.inventory.ui.dsl.event.UIClickEvent;
import amata1219.amalib.inventory.ui.dsl.event.UICloseEvent;
import amata1219.amalib.inventory.ui.dsl.event.UIOpenEvent;
import amata1219.amalib.inventory.ui.option.InventoryOption;

public class InventoryLayout {

	public final Player player;
	public final InventoryUI ui;
	public final InventoryOption option;

	public String title;

	private final HashMap<Integer, Slot> slots = new HashMap<>();
	private Applier<Slot> defaultSlot = (slot) -> {};

	private Consumer<UIClickEvent> actionOnClick = (event) -> {};
	private Consumer<UIOpenEvent> actionOnOpen = (event) -> {};
	private Consumer<UICloseEvent> actionOnClose = (event) -> {};

	public InventoryLayout(Player player, InventoryUI ui, InventoryOption option){
		this.player = player;
		this.ui = ui;
		this.option = option;
	}

	public Inventory buildInventory(){
		Inventory inventory = createInventory(ui, option, title);

		for(int slotIndex = 0; slotIndex < inventory.getSize(); slotIndex++)
			inventory.setItem(slotIndex, getSlotAt(slotIndex).buildIcon().toItemStack());

		return inventory;
	}

	public Slot getSlotAt(int slotIndex){
		return slots.containsKey(slotIndex) ? slots.get(slotIndex) : defaultSlot.apply(new Slot());
	}

	public void defaultSlot(Applier<Slot> slotApplier){
		Validate.notNull(slotApplier, "Slot applier can not be null");
		defaultSlot = slotApplier;
	}

	public void put(Applier<Slot> slotApplier, IntStream range){
		put(slotApplier, range.toArray());
	}

	public void put(Applier<Slot> slotApplier, int... slotIndexes){
		for(int slotIndex : slotIndexes)
			slots.put(slotIndex, slotApplier.apply(new Slot()));
	}

	public void remove(IntStream range){
		remove(range.toArray());
	}

	public void remove(int... slotIndexes){
		for(int slotIndex : slotIndexes)
			slots.remove(slotIndex);
	}

	public void onClick(Consumer<UIClickEvent> action){
		Validate.notNull(action, "Action can not be null");
		actionOnClick = action;
	}

	public void fire(UIClickEvent event){
		actionOnClick.accept(event);
	}

	public void onOpen(Consumer<UIOpenEvent> action){
		Validate.notNull(action, "Action can not be null");
		actionOnOpen = action;
	}

	public void fire(UIOpenEvent event){
		actionOnOpen.accept(event);
	}

	public void onClose(Consumer<UICloseEvent> action){
		Validate.notNull(action, "Action can not be null");
		actionOnClose = action;
	}

	public void fire(UICloseEvent event){
		actionOnClose.accept(event);
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

package amata1219.amalib.inventory.ui;

import java.util.HashMap;
import java.util.function.Consumer;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class InventoryLayout {

	private final InventoryUI ui;
	private final InventoryInformation information;

	private String title;
	private final HashMap<Integer, Slot> slots = new HashMap<>();
	private Applicator<Slot> defaultSlotApplicator;
	private Consumer<InventoryOpenEvent> actionOnOpen;
	private Consumer<InventoryCloseEvent> actionOnClose;

	public InventoryLayout(InventoryUI ui, InventoryInformation information){
		this.ui = ui;
		this.information = information;
	}

	public Slot getSlotAt(int slotIndex){
		return slots.containsKey(slotIndex) ? slots.get(slotIndex) : defaultSlotApplicator.apply(new Slot());
	}

	public Inventory buildInventory(){
		Inventory inventory = createInventory(ui, information, title);

		for(int slotIndex = 0; slotIndex < inventory.getSize(); slotIndex++)
			inventory.setItem(slotIndex, getSlotAt(slotIndex));

		return inventory;
	}

	public void defaultSlot(Applicator<Slot> applicate){
		this.defaultSlotApplicator = applicate;
	}

	public void put(Applicator<Slot> applicate, int... slotIndexes){

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

	/*
    fun put(vararg positions: Int, build: SlotCondiment) {
        positions
                .map { position -> position to Slot().apply { build(position) } }
                .filter { (_, slot) -> slot.isAvailable() }
                .forEach { (position, slot) -> slotMap[position] = slot }
    }

    fun put(positionRange: IntRange, build: SlotCondiment) {
        put(positions = *positionRange.toList().toIntArray(), build = build)
    }

    private fun createInventory(inventoryHolder: InventoryHolder, inventoryInformation: InventoryInformation, title: String?): Inventory {
        return if (inventoryInformation.size != null) {
            if (title != null) {
                Bukkit.createInventory(inventoryHolder, inventoryInformation.size, title)
            } else {
                Bukkit.createInventory(inventoryHolder, inventoryInformation.size)
            }
        } else {
            if (title != null) {
                Bukkit.createInventory(inventoryHolder, inventoryInformation.type, title)
            } else {
                Bukkit.createInventory(inventoryHolder, inventoryInformation.type)
            }
        }
    }

	 */

}

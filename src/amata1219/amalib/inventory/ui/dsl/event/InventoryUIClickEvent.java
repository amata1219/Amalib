package amata1219.amalib.inventory.ui.dsl.event;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUIClickEvent extends InventoryEvent {

	public final ClickType click;
	public final InventoryAction action;
	public final InventoryType.SlotType slotType;
	public final int slot;
	public final int rawSlot;
	public final ItemStack currentItemStack;
	public final int hotbarKey;
	public final ItemStack cursorItemStack;
	public final Inventory clickedInventory;
	private InventoryClickEvent event;

	public InventoryUIClickEvent(InventoryClickEvent event) {
		super(event.getWhoClicked(), event);
		click = event.getClick();
		action = event.getAction();
		slotType = event.getSlotType();
		slot = event.getSlot();
		rawSlot = event.getRawSlot();
		currentItemStack = event.getCurrentItem();
		hotbarKey = event.getHotbarButton();
		cursorItemStack = event.getCursor();
		clickedInventory = event.getClickedInventory();
		this.event = event;
	}

	public boolean isRightClick() {
		return event.isRightClick();
	}

	public boolean isLeftClick() {
		return event.isLeftClick();
	}

	public boolean isShiftClick() {
		return event.isShiftClick();
	}

	public void setCursor(ItemStack item) {
		view.setCursor(item);
	}

	public void setCurrentItem(ItemStack item) {
		event.setCurrentItem(item);
	}

}

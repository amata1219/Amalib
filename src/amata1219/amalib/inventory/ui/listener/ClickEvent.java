package amata1219.amalib.inventory.ui.listener;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import amata1219.amalib.inventory.ui.dsl.component.Icon;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;

public class ClickEvent extends UIEvent {

	public final ClickType click;
	public final InventoryAction action;
	public final InventoryType.SlotType slotType;
	public final int slot;
	public final int rawSlot;
	public final Icon currentIcon;
	public final int hotbarKey;
	public final Icon cursorIcon;
	public final Inventory clickedInventory;

	public ClickEvent(InventoryLayout layout, InventoryClickEvent event) {
		super(layout, event.getWhoClicked(), event);

		click = event.getClick();
		action = event.getAction();

		slotType = event.getSlotType();
		slot = event.getSlot();
		rawSlot = event.getRawSlot();

		ItemStack currentItem = event.getCurrentItem();
		currentIcon = currentItem != null ? new Icon(currentItem) : null;

		hotbarKey = event.getHotbarButton();

		ItemStack cursorItem = event.getCursor();
		cursorIcon = cursorItem != null ? new Icon(cursorItem) : null;

		clickedInventory = event.getClickedInventory();
	}

	public boolean isOutOfInventoryClick(){
		return clickedInventory == null;
	}

	public boolean isRightClick() {
		return getBukkitClickEvent().isRightClick();
	}

	public boolean isLeftClick() {
		return getBukkitClickEvent().isLeftClick();
	}

	public boolean isShiftClick() {
		return getBukkitClickEvent().isShiftClick();
	}

	public InventoryClickEvent getBukkitClickEvent(){
		return ((InventoryClickEvent) bukkitEvent);
	}

}

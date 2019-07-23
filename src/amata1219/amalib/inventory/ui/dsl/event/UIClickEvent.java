package amata1219.amalib.inventory.ui.dsl.event;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import amata1219.amalib.inventory.ui.dsl.component.Icon;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;

public class UIClickEvent extends UIEvent {

	public final ClickType click;
	public final InventoryAction action;
	public final InventoryType.SlotType slotType;
	public final int slot;
	public final int rawSlot;
	public final Icon currentIcon;
	public final int hotbarKey;
	public final Icon cursorIcon;
	public final Inventory clickedInventory;

	public UIClickEvent(InventoryLayout layout, InventoryClickEvent event) {
		super(layout, event.getWhoClicked(), event);

		click = event.getClick();
		action = event.getAction();

		slotType = event.getSlotType();
		slot = event.getSlot();
		rawSlot = event.getRawSlot();

		currentIcon = new Icon();
		currentIcon.overwrite(event.getCurrentItem());

		hotbarKey = event.getHotbarButton();

		cursorIcon = new Icon();
		cursorIcon.overwrite(event.getCursor());

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

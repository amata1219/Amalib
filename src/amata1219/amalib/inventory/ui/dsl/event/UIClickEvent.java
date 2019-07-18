package amata1219.amalib.inventory.ui.dsl.event;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UIClickEvent extends UIEvent {

	public final ClickType click;
	public final InventoryAction action;
	public final InventoryType.SlotType slotType;
	public final int slot;
	public final int rawSlot;
	public final ItemStack currentItemStack;
	public final int hotbarKey;
	public final ItemStack cursorItemStack;
	public final Inventory clickedInventory;

	public UIClickEvent(InventoryClickEvent event) {
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

	public void setCursor(ItemStack item) {
		view.setCursor(item);
	}

	public void setCurrentItem(ItemStack item) {
		getBukkitClickEvent().setCurrentItem(item);
	}

	public InventoryClickEvent getBukkitClickEvent(){
		return ((InventoryClickEvent) bukkitEvent);
	}

}

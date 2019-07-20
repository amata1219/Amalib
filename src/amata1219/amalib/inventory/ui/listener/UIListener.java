package amata1219.amalib.inventory.ui.listener;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import amata1219.amalib.inventory.ui.dsl.InventoryUI;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;
import amata1219.amalib.inventory.ui.dsl.component.Slot;
import amata1219.amalib.inventory.ui.dsl.event.UIClickEvent;
import amata1219.amalib.inventory.ui.dsl.event.UICloseEvent;
import amata1219.amalib.inventory.ui.dsl.event.UIOpenEvent;

public class UIListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event){
		Inventory displayed = event.getInventory();
		Inventory clicked = event.getClickedInventory();

		InventoryLayout layout = getLayout(displayed != null && clicked == null ? displayed.getHolder() : clicked.getHolder(), event.getWhoClicked());

		if(layout == null)
			return;

		UIClickEvent clickEvent = new UIClickEvent(layout, event);

		Slot slot = layout.getSlotAt(event.getSlot());

		event.setCancelled(!slot.editable);

		slot.fire(clickEvent);
		layout.fire(clickEvent);
	}

	@EventHandler
	public void onOpen(InventoryOpenEvent event){
		InventoryLayout layout = getLayout(event.getInventory().getHolder(), event.getPlayer());

		if(layout != null)
			layout.fire(new UIOpenEvent(layout, event));
	}

	@EventHandler
	public void onClose(InventoryCloseEvent event){
		InventoryLayout layout = getLayout(event.getInventory().getHolder(), event.getPlayer());

		if(layout != null)
			layout.fire(new UICloseEvent(layout, event));
	}

	private InventoryLayout getLayout(InventoryHolder holder, HumanEntity human){
		return holder instanceof InventoryUI && human instanceof Player ? ((InventoryUI) holder).layout().apply((Player) human) : null;
	}
}

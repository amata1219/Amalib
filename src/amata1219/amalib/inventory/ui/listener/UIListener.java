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

public class UIListener implements Listener {

	@EventHandler
	public void onOpen(InventoryOpenEvent event){
		InventoryLayout layout = getLayout(event.getInventory().getHolder(), event.getPlayer());

		if(layout != null)
			layout.fire(event);
	}

	@EventHandler
	public void onClose(InventoryCloseEvent event){
		InventoryLayout layout = getLayout(event.getInventory().getHolder(), event.getPlayer());

		if(layout != null)
			layout.fire(event);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event){
		Inventory displayed = event.getInventory();
		Inventory clicked = event.getClickedInventory();

		HumanEntity human = event.getWhoClicked();

		InventoryLayout layout = null;

		if(displayed != null && clicked == null){
			layout = getLayout(displayed.getHolder(), human);

			if(layout == null)
				return;

			layout.fire(event);
		}else{
			layout = getLayout(clicked.getHolder(), human);

			if(layout == null)
				return;

			layout.getSlotAt(event.getSlot()).fire(event);
		}

		event.setCancelled(true);
	}

	private InventoryLayout getLayout(InventoryHolder holder, HumanEntity human){
		return holder instanceof InventoryUI && human instanceof Player ? ((InventoryUI) holder).layout().apply((Player) human) : null;
	}
}

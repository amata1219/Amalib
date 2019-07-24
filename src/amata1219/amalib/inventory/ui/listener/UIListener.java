package amata1219.amalib.inventory.ui.listener;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;

import amata1219.amalib.inventory.ui.dsl.InventoryUI;
import amata1219.amalib.inventory.ui.dsl.component.Icon;
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

		Icon currentIcon = clickEvent.currentIcon;
		currentIcon.overwrite(currentIcon.basedItemStack);

		Icon cursorIcon = clickEvent.cursorIcon;
		cursorIcon.overwrite(cursorIcon.basedItemStack);
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

	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		InventoryView opened = player.getOpenInventory();
		if(opened == null)
			return;

		InventoryLayout layout = null;
		Inventory top = opened.getTopInventory();
		if(top != null){
			layout = getLayout(top.getHolder(), player);
			if(layout != null){
				player.closeInventory();
				return;
			}
		}

		Inventory bottom = opened.getBottomInventory();
		if(bottom != null){
			layout = getLayout(bottom.getHolder(), player);
			if(layout != null)
				player.closeInventory();
		}
	}

	public static InventoryLayout getLayout(InventoryHolder holder, HumanEntity human){
		return holder instanceof InventoryUI && human instanceof Player ? ((InventoryUI) holder).layout().apply((Player) human) : null;
	}
}

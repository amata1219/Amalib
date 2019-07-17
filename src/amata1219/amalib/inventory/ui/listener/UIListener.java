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

public class UIListener implements Listener {

	@EventHandler
	public void onOpen(InventoryOpenEvent event){
		InventoryHolder holder = event.getInventory().getHolder();
		HumanEntity human = event.getPlayer();
		if(!isInventoryUI(holder) || !isPlayer(human))
			return;

		((InventoryUI) holder).openInventory((Player) human);
	}

	@EventHandler
	public void onClose(InventoryCloseEvent event){
		InventoryHolder holder = event.getInventory().getHolder();
		HumanEntity human = event.getPlayer();
		if(!isInventoryUI(holder) || !isPlayer(human))
			return;
	}

	@EventHandler
	public void onClick(InventoryClickEvent event){
		Inventory inventory = event.getClickedInventory();
		if(inventory == null)
			return;

		InventoryHolder holder = event.getInventory().getHolder();
		HumanEntity human = event.getWhoClicked();
		if(!isInventoryUI(holder) || !isPlayer(human))
			return;
	}

	private void a(Inventory inventory, HumanEntity human){
		if(inventory == null)
			return;

		InventoryHolder holder = inventory.getHolder();

	}

	public boolean isInventoryUI(InventoryHolder holder){
		return holder instanceof InventoryUI;
	}

	public boolean isPlayer(HumanEntity human){
		return human instanceof Player;
	}

}

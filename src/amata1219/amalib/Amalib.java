package amata1219.amalib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import amata1219.amalib.enchantment.GleamEnchantment;
import amata1219.amalib.event.PlayerJumpEvent;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;
import amata1219.amalib.inventory.ui.listener.UIListener;

public class Amalib extends Plugin {

	private static Amalib plugin;

	@Override
	public void onEnable(){
		plugin = this;

		registerCommands(

		);

		registerListeners(
			UIListener.listener,
			PlayerJumpEvent.listener
		);

		registerEnchantments(
			GleamEnchantment.GLEAM_ENCHANTMENT
		);
	}

	@Override
	public void onDisable(){
		super.onDisable();

		closeAllInventoryUI();
	}

	public static Amalib getPlugin(){
		return plugin;
	}

	private void closeAllInventoryUI(){
		for(Player player : Bukkit.getOnlinePlayers()){
			InventoryView opened = player.getOpenInventory();
			if(opened == null)
				continue;

			tryCloseInventoryUI(player, opened.getTopInventory());
			tryCloseInventoryUI(player, opened.getBottomInventory());
		}
	}

	private void tryCloseInventoryUI(Player player, Inventory inventory){
		if(inventory == null)
			return;

		InventoryLayout layout = UIListener.getLayout(inventory.getHolder(), player);
		if(layout != null)
			player.closeInventory();
	}

}

package amata1219.amalib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import amata1219.amalib.enchantment.GleamEnchantment;
import amata1219.amalib.event.PlayerJumpEvent.PlayerJumpListener;
import amata1219.amalib.inventory.ui.dsl.InventoryUI;
import amata1219.amalib.inventory.ui.listener.UIListener;

public class Amalib extends Plugin {

	private static Amalib plugin;

	@Override
	public void onEnable(){
		plugin = this;

		registerCommands(

		);

		registerListeners(
			new UIListener(),
			new PlayerJumpListener()
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

			closeInventoryUI(player, opened.getTopInventory());
			closeInventoryUI(player, opened.getBottomInventory());
		}
	}

	private void closeInventoryUI(Player player, Inventory inventory){
		if(inventory != null && inventory.getHolder() instanceof InventoryUI) player.closeInventory();
	}

}

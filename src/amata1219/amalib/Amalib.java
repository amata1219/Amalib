package amata1219.amalib;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import amata1219.amalib.event.PlayerJumpEvent;
import amata1219.amalib.inventory.ui.dsl.component.Icon;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;
import amata1219.amalib.inventory.ui.listener.UIListener;
import amata1219.amalib.reflection.Reflection;

public class Amalib extends Plugin {

	private static Amalib plugin;

	@Override
	public void onEnable(){
		plugin = this;

		registerGleamEnchantment();

		registerCommands(

		);

		registerListeners(
			UIListener.listener,
			PlayerJumpEvent.listener
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

	private void registerGleamEnchantment(){
		Field acceptingNew = Reflection.getField(Enchantment.class, "acceptingNew");

		//状態を保存する
		final boolean accept = Reflection.getFieldValue(acceptingNew, null);

		//エンチャント登録が許可された状態にする
		Reflection.setFieldValue(acceptingNew, null, true);

		try{
			Enchantment.registerEnchantment(Icon.GLEAM_ENCHANTMENT);
		}catch(Exception e){
			//既に登録されていれば問題無いので無視する
		}finally{
			//元の状態に戻す
			Reflection.setFieldValue(acceptingNew, null, accept);
		}
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

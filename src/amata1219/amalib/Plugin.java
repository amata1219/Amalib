package amata1219.amalib;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.java.JavaPlugin;

import amata1219.amalib.command.Arguments;
import amata1219.amalib.command.Command;
import amata1219.amalib.command.Sender;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;
import amata1219.amalib.inventory.ui.listener.UIListener;

public class Plugin extends JavaPlugin {

	private final HashMap<String, Command> commands = new HashMap<>();

	@Override
	public void onEnable(){
		registerListeners(
			new UIListener()
		);
	}

	@Override
	public void onDisable(){
		closeAllInventoryUI();

		HandlerList.unregisterAll((JavaPlugin) this);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args){
		commands.get(command.getName()).onCommand(new Sender(sender), new Arguments(args));
		return true;
	}

	protected void registerCommands(Command... commands){
		for(Command command : commands){
			//コマンドのクラス名を取得する
			String className = command.getClass().getSimpleName();

			//接尾辞のCommandを削除し小文字化した物をコマンド名とする
			String commandName = className.substring(0, className.length() - 7).toLowerCase();

			//コマンド名とコマンドを結び付けて登録する
			this.commands.put(commandName, command);
		}
	}

	protected void registerListeners(Listener... listeners){
		for(Listener listener : listeners)
			getServer().getPluginManager().registerEvents(listener, this);
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

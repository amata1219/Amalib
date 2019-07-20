package amata1219.amalib;

import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import amata1219.amalib.command.Arguments;
import amata1219.amalib.command.Command;
import amata1219.amalib.command.Sender;
import amata1219.amalib.command.TestCommand;

public class Amalib extends JavaPlugin {

	private static Amalib plugin;

	private final HashMap<String, Command> commands = new HashMap<>();

	@Override
	public void onEnable(){
		plugin = this;

		registerCommands(
			new TestCommand()
		);
	}

	@Override
	public void onDisable(){
		HandlerList.unregisterAll((JavaPlugin) this);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args){
		commands.get(command.getName()).onCommand(new Sender(sender), new Arguments(args));
		return true;
	}

	public static Amalib getPlugin(){
		return plugin;
	}

	public void registerCommands(Command... commands){
		for(Command command : commands){
			//コマンドのクラス名を取得する
			String className = command.getClass().getSimpleName();

			//接尾辞のCommandを削除し小文字化した物をコマンド名とする
			String commandName = className.substring(0, className.length() - 7).toLowerCase();

			//コマンド名とコマンドを結び付けて登録する
			this.commands.put(commandName, command);
		}
	}

	public void registerListeners(Listener... listeners){
		for(Listener listener : listeners)
			getServer().getPluginManager().registerEvents(listener, this);
	}

}

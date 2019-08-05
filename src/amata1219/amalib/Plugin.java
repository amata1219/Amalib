package amata1219.amalib;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import amata1219.amalib.command.Arguments;
import amata1219.amalib.command.Command;
import amata1219.amalib.command.Sender;
import amata1219.amalib.reflection.Reflection;

public class Plugin extends JavaPlugin {

	private final HashMap<String, Command> commands = new HashMap<>();

	@Override
	public void onDisable(){
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

	protected void registerEnchantments(Enchantment... enchantments){
		Field acceptingNew = Reflection.getField(Enchantment.class, "acceptingNew");

		//状態を保存する
		final boolean accept = Reflection.getFieldValue(acceptingNew, null);

		//エンチャント登録が許可された状態にする
		Reflection.setFieldValue(acceptingNew, null, true);

		try{
			//エンチャントを登録する
			for(Enchantment enchantment : enchantments) Enchantment.registerEnchantment(enchantment);
		}catch(Exception e){
			//既に登録されていれば問題無いので無視する
		}finally{
			//元の状態に戻す
			Reflection.setFieldValue(acceptingNew, null, accept);
		}
	}

}

package amata1219.amalib;

import org.bukkit.plugin.java.JavaPlugin;

public class Amalib extends JavaPlugin {

	private static Amalib plugin;

	@Override
	public void onEnable(){
		plugin = this;
	}

	@Override
	public void onDisable(){

	}

	public static Amalib getPlugin(){
		return plugin;
	}

}

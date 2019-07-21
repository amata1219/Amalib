package amata1219.amalib;

import amata1219.amalib.inventory.ui.listener.UIListener;

public class Amalib extends Plugin {

	private static Amalib plugin;

	@Override
	public void onEnable(){
		plugin = this;

		registerCommands(

		);

		registerListeners(
			new UIListener()
		);
	}

	@Override
	public void onDisable(){
		super.onDisable();
	}

	public static Amalib getPlugin(){
		return plugin;
	}

}

package amata1219.amalib;

import amata1219.amalib.command.TestCommand;

public class Amalib extends Plugin {

	private static Amalib plugin;

	@Override
	public void onEnable(){
		plugin = this;

		registerCommands(

		);

		registerCommands(
			new TestCommand()
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

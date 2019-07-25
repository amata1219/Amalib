package amata1219.amalib.command;

public interface Command {

	void onCommand(Sender sender, Arguments args);

	default boolean blockNonPlayer(Sender sender){
		if(sender.isPlayerCommandSender())
			return false;

		sender.warn("ゲーム内から実行して下さい。");
		return true;
	}

}

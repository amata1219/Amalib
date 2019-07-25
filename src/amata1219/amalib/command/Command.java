package amata1219.amalib.command;

import amata1219.amalib.exception.NotImplementedException;

public interface Command {

	void onCommand(Sender sender, Arguments args);

	default boolean blockNonPlayer(Sender sender){
		if(sender.isPlayerCommandSender())
			return false;

		sender.warn("ゲーム内から実行して下さい。");
		return true;
	}

	default void sendCommandSyntax(Sender sender, int number){
		try {
			sender.warn(commandSyntax().split(", ")[number]);
		} catch (NotImplementedException e) {
			e.printStackTrace();
		}
	}

	default String commandSyntax() throws NotImplementedException {
		throw new NotImplementedException("amata1219.amalib.command.Command#commandSyntax() is undefined.");
	}

}

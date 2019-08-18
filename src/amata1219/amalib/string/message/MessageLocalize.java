package amata1219.amalib.string.message;

import org.bukkit.entity.Player;

import amata1219.amalib.string.StringLocalize;

public class MessageLocalize {

	public static Message localize(String text, Player player){
		return Message.wrap(StringLocalize.localize(text, player));
	}

	public static Message template(String text, Player player, Object... objects){
		return Message.wrap(StringLocalize.template(text, player, objects));
	}

	public static Message color(String text, Player player){
		return Message.wrap(StringLocalize.color(text, player));
	}

	public static Message applyAll(String text, Player player, Object... objects){
		return Message.wrap(StringLocalize.applyAll(text, player, objects));
	}


}

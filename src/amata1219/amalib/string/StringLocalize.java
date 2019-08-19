package amata1219.amalib.string;

import org.bukkit.entity.Player;

public class StringLocalize {

	public static String localize(String text, Player player){
		int separatorFirstIndex = text.indexOf(" | ");

		return player.getLocale().equals("ja_jp") ? text.substring(0, separatorFirstIndex) : text.substring(separatorFirstIndex + 3);
	}

	public static String template(String text, Player player, Object... objects){
		return StringTemplate.apply(localize(text, player), objects);
	}

	public static String color(String text, Player player){
		return StringColor.color(localize(text, player));
	}

	public static String applyAll(String text, Player player, Object... objects){
		return StringColor.color(template(text, player, objects));
	}

}

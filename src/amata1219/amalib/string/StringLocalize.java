package amata1219.amalib.string;

import org.bukkit.entity.Player;

public class StringLocalize {

	public static String localize(String text, Player player){
		return text.split(" | ", 2)[player.getLocale().equals("ja_jp") ? 0 : 1];
	}

	public static String template(String text, Player player, Object... objects){
		return StringTemplate.apply(localize(text, player), objects);
	}

	public static String color(String text, Player player){
		return StringColor.color(localize(text, player));
	}

	public static String ctemplate(String text, Player player, Object... objects){
		return StringColor.color(template(text, player, objects));
	}

}

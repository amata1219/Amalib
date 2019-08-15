package amata1219.amalib.string;

import org.bukkit.entity.Player;

public class StringLocalize {

	public static String localize(String text, Player player){
		return text.split(" | ", 2)[player.getLocale().equals("ja_jp") ? 0 : 1];
	}

}

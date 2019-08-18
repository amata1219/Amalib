package amata1219.amalib.string.message;

import org.bukkit.entity.Player;

import amata1219.amalib.string.StringLocalize;

public class Localizer {

	public final Player player;

	public Localizer(Player player){
		this.player = player;
	}

	public String localize(String text){
		return StringLocalize.localize(text, player);
	}

	public String template(String text, Object... objects){
		return StringLocalize.template(text, player, objects);
	}

	public String color(String text){
		return StringLocalize.color(text, player);
	}

	public String applyAll(String text, Object... objects){
		return StringLocalize.applyAll(text, player, objects);
	}

}

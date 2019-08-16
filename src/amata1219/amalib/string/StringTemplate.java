package amata1219.amalib.string;

import org.bukkit.entity.Player;

public class StringTemplate {

	public static String apply(String text, Object... objects){
		return apply(text, '$', objects);
	}

	public static String apply(String text, char alternateCode, Object... objects){
		for(int i = 0; i < objects.length; i++)
			text = text.replace(alternateCode + String.valueOf(i), objects[i].toString());

		return text;
	}

	//装飾コードの適用も行う
	public static String capply(String text, Object... objects){
		return StringColor.color(apply(text, objects));
	}

	//ローカライズと装飾コードの適用も行う
	public static String clapply(String text, Player player, Object... objects){
		return StringColor.color(StringLocalize.apply(apply(text, objects), player));
	}
}

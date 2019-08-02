package amata1219.amalib.text;

public class TextTemplate {

	public static String format(String text, Object... objects){
		return format(text, "$", objects);
	}

	public static String format(String text, String alternateCode, Object... objects){
		for(int i = 0; i < objects.length; i++)
			text = text.replace(alternateCode + i, objects[i].toString());

		return text;
	}

	public static String formatWithColor(String text, Object... objects){
		return format(TextColor.color(text), objects);
	}

}

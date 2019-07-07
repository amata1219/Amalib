package amata1219.amalib;

public class StringTemplate {

	public static String format(String text, Object... objects){
		return format(text, "$", objects);
	}

	public static String format(String text, String target, Object... objects){
		for(int i = 0; i < objects.length; i++)
			text = text.replace(target + i, objects[i].toString());

		return text;
	}

}

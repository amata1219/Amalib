package amata1219.amalib.string;

public class StringTemplate {

	public static String apply(String text, Object... objects){
		return apply(text, '$', objects);
	}

	public static String apply(String text, char alternateCode, Object... objects){
		for(int i = 0; i < objects.length; i++)
			text = text.replace(alternateCode + String.valueOf(i), objects[i].toString());

		return text;
	}

	public static String capply(String text, Object... objects){
		return apply(StringColor.color('&', text), objects);
	}

}

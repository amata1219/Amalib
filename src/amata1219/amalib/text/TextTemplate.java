package amata1219.amalib.text;

import amata1219.amalib.string.StringTemplate;

public class TextTemplate {

	public static Text apply(String text, Object... objects){
		return Text.wrap(StringTemplate.apply(text, objects));
	}

	public static Text apply(String text, String alternateCode, Object... objects){
		return Text.wrap(StringTemplate.apply(text, alternateCode, objects));
	}

	public static Text applyWithColor(String text, Object... objects){
		return Text.wrap(StringTemplate.applyWithColor(text, objects));
	}

}

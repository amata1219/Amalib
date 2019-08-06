package amata1219.amalib.message;

import amata1219.amalib.string.StringTemplate;

public class MessageTemplate {

	public static Message apply(String text, Object... objects){
		return Message.wrap(StringTemplate.apply(text, objects));
	}

	public static Message apply(String text, String alternateCode, Object... objects){
		return Message.wrap(StringTemplate.apply(text, alternateCode, objects));
	}

	public static Message applyWithColor(String text, Object... objects){
		return Message.wrap(StringTemplate.capply(text, objects));
	}

}

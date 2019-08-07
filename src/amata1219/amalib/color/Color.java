package amata1219.amalib.color;

import java.util.Random;

import amata1219.amalib.string.StringSplit;
import amata1219.amalib.string.StringTemplate;

public class Color {

	private static final Random random = new Random();

	public static Color deserialize(String text){
		int[] values = StringSplit.splitToIntArguments(text);
		return new Color(values[0], values[1], values[2]);
	}

	public final int red, green, blue;

	public Color(int red, int green, int blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int adjustRed(int width){
		return adjust(red, width);
	}

	public int adjustGreen(int width){
		return adjust(green, width);
	}

	public int adjustBlue(int width){
		return adjust(blue, width);
	}

	private int adjust(int value, int width){
		return Math.max(Math.min(value + random.nextInt(width) - (width / 2 - 1), 255), 0);
	}

	public String serialize(){
		return StringTemplate.apply("$0,$1,$2", red, green, blue);
	}

}

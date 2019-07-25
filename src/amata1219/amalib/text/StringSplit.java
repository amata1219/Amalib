package amata1219.amalib.text;

import java.util.Arrays;

public class StringSplit {

	public static int[] splitToIntArguments(String text){
		return Arrays.stream(text.split(","))
						.mapToInt(Integer::parseInt)
						.toArray();
	}

}

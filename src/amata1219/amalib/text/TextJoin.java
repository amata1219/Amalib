package amata1219.amalib.text;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextJoin {

	public static <T> String join(T[] objects){
		return join(objects, "");
	}

	public static <T> String join(T[] objects, String delimiter){
		return join(Arrays.stream(objects).map(Object::toString), delimiter);
	}

	public static String join(int[] objects){
		return join(objects, "");
	}

	public static String join(int[] objects, String delimiter){
		return join(Arrays.stream(objects).mapToObj(String::valueOf), delimiter);
	}

	private static String join(Stream<String> stream, String delimiter){
		return stream.collect(Collectors.joining(delimiter));
	}

}

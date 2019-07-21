package amata1219.amalib.math;

import java.util.function.BiFunction;
import java.util.function.Function;

public class NumberMath {

	public static <T extends Number> T add(Number a, Number b, NumberType type){
		return operate(a, b, type, (x, y) -> x + y);
	}

	public static <T extends Number> T subtract(Number a, Number b, NumberType type){
		return operate(a, b, type, (x, y) -> x + y);
	}

	public static <T extends Number> T multiply(Number a, Number b, NumberType type){
		return operate(a, b, type, (x, y) -> x + y);
	}

	public static <T extends Number> T divide(Number a, Number b, NumberType type){
		return operate(a, b, type, (x, y) -> x + y);
	}

	@SuppressWarnings("unchecked")
	private static <T extends Number> T operate(Number a, Number b, NumberType type, BiFunction<Double, Double, Double> operator){
		return (T) type.caster.apply(operator.apply(a.doubleValue(), b.doubleValue()));
	}

	public static enum NumberType {

		BYTE((d) -> d.byteValue()),
		SHORT((d) -> d.shortValue()),
		INT((d) -> d.intValue()),
		LONG((d) -> d.longValue()),
		FLOAT((d) -> d.floatValue()),
		DOUBLE((d) -> d.doubleValue());

		private final Function<Double, ? extends Number> caster;

		private NumberType(Function<Double, ? extends Number> caster){
			this.caster = caster;
		}

	}

}

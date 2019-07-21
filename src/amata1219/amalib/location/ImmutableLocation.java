package amata1219.amalib.location;

import java.util.function.BiFunction;

import org.bukkit.World;

import amata1219.amalib.text.StringTemplate;

public class ImmutableLocation<N extends Number> {

	public final World world;
	public final N x, y, z;

	public ImmutableLocation(World world, N x, N y, N z){
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@SuppressWarnings("unchecked")
	public <T extends Number> ImmutableLocation<T> relative(NumberType type, T x, T y, T z){
		return new ImmutableLocation<T>(world, (T) type.subtract(x, this.x), (T) type.subtract(y, this.y), (T) type.subtract(z, this.z));
	}

	@Override
	public String toString(){
		return StringTemplate.format("$0,$1,$2,$3", world.getName(), x, y, z);
	}

	public static enum NumberType {

		BYTE((n1, n2) -> n1.byteValue() - n2.byteValue()),
		SHORT((n1, n2) -> n1.shortValue() - n2.shortValue()),
		INT((n1, n2) -> n1.intValue() - n2.intValue()),
		LONG((n1, n2) -> n1.longValue() - n2.longValue()),
		FLOAT((n1, n2) -> n1.floatValue() - n2.floatValue()),
		DOUBLE((n1, n2) -> n1.doubleValue() - n2.doubleValue());

		private final BiFunction<Number, Number, Number> converter;

		private NumberType(BiFunction<Number, Number, Number> converter){
			this.converter = converter;
		}

		public Number subtract(Number n1, Number n2){
			return converter.apply(n1, n2);
		}

	}

}

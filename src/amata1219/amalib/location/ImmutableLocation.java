package amata1219.amalib.location;

import org.bukkit.World;

import amata1219.amalib.math.NumberMath;
import amata1219.amalib.math.NumberMath.NumberType;
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

	public <T extends Number> ImmutableLocation<T> add(NumberType type, T x, T y, T z){
		return new ImmutableLocation<T>(world, NumberMath.add(x, this.x, type), NumberMath.add(y, this.y, type), NumberMath.add(z, this.z, type));
	}

	public <T extends Number> ImmutableLocation<T> relative(NumberType type, T x, T y, T z){
		return new ImmutableLocation<T>(world, NumberMath.subtract(x, this.x, type), NumberMath.subtract(y, this.y, type), NumberMath.subtract(z, this.z, type));
	}

	@Override
	public String toString(){
		return StringTemplate.format("$0,$1,$2,$3", world.getName(), x, y, z);
	}


}

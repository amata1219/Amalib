package amata1219.amalib.location;

import org.bukkit.World;

public class ImmutableEntityLocation extends ImmutableLocation {

	public final double x, y, z;

	public static ImmutableEntityLocation at(World world, double x, double y, double z){
		return new ImmutableEntityLocation(world, x, y, z);
	}

	public ImmutableEntityLocation(World world, double x, double y, double z) {
		super(world);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getZ() {
		return z;
	}
}

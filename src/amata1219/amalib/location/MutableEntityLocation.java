package amata1219.amalib.location;

import org.bukkit.World;

public class MutableEntityLocation extends MutableLocation implements EntityLocation {

	public double x, y, z;

	public MutableEntityLocation(World world, double x, double y, double z) {
		super(world);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public double getEntityX() {
		return x;
	}

	@Override
	public double getEntityY() {
		return y;
	}

	@Override
	public double getEntityZ() {
		return z;
	}

	@Override
	public ImmutableEntityLocation asImmutable() {
		return new ImmutableEntityLocation(world, x, y, z);
	}

}

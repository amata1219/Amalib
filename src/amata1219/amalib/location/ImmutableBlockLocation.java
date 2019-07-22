package amata1219.amalib.location;

import org.bukkit.World;

public class ImmutableBlockLocation extends ImmutableLocation {

	public final int x, y, z;

	public static ImmutableBlockLocation at(World world, int x, int y, int z){
		return new ImmutableBlockLocation(world, x, y, z);
	}

	public ImmutableBlockLocation(World world, int x, int y, int z) {
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

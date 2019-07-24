package amata1219.amalib.location;

import org.bukkit.World;

public class MutableBlockLocation extends MutableLocation implements BlockLocation {

	public int x, y, z;

	public MutableBlockLocation(World world, int x, int y, int z) {
		super(world);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public int getBlockX() {
		return x;
	}

	@Override
	public int getBlockY() {
		return y;
	}

	@Override
	public int getBlockZ() {
		return z;
	}

	@Override
	public ImmutableBlockLocation asImmutable() {
		return new ImmutableBlockLocation(world, x, y, z);
	}

}

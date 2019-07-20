package amata1219.amalib.location;

import org.bukkit.World;

public class LowPrecisionImmutableLocation extends ImmutableLocation<Integer> {

	public LowPrecisionImmutableLocation(World world, int x, int y, int z) {
		super(world, x, y, z);
	}

	@Override
	public ImmutableLocation<Integer> relative(Integer x, Integer y, Integer z) {
		return new LowPrecisionImmutableLocation(world, this.x - x, this.y - y, this.z - z);
	}

}

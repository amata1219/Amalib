package amata1219.amalib.location;

import org.bukkit.World;

public class HighPrecisionImmutableLocation extends ImmutableLocation<Double> {

	public HighPrecisionImmutableLocation(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public ImmutableLocation<Double> relative(Double x, Double y, Double z) {
		return new HighPrecisionImmutableLocation(world, this.x - x, this.y - y, this.z - z);
	}

}

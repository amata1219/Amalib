package amata1219.amalib.location;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class ImmutableEntityLocation extends ImmutableLocation implements EntityLocation {

	public static ImmutableEntityLocation deserialize(String text){
		String[] data = text.split(",");
		return new ImmutableEntityLocation(Bukkit.getWorld(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]), Float.parseFloat(data[4]), Float.parseFloat(data[5]));
	}

	public final double x, y, z;

	public ImmutableEntityLocation(World world, double x, double y, double z, float yaw, float pitch) {
		super(world, yaw, pitch);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public ImmutableEntityLocation(World world, double x, double y, double z){
		this(world, x, y, z, 0, 0);
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
	public ImmutableEntityLocation add(double x, double y, double z) {
		return new ImmutableEntityLocation(world, this.x + x, this.y + y, this.z + z, yaw, pitch);
	}

	@Override
	public ImmutableEntityLocation relative(double x, double y, double z) {
		return new ImmutableEntityLocation(world, x - this.x, y - this.y, z - this.z, yaw, pitch);
	}

	@Override
	public ImmutableBlockLocation asBlockLocation() {
		return new ImmutableBlockLocation(world, getBlockX(), getBlockY(), getBlockZ(), yaw, pitch);
	}

	@Override
	public MutableEntityLocation asMutable() {
		return new MutableEntityLocation(world, x, y, z, yaw, pitch);
	}

}

package amata1219.amalib.location;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class MutableBlockLocation extends MutableLocation implements BlockLocation {

	public static MutableBlockLocation deserialize(String text){
		String[] data = text.split(",");
		return new MutableBlockLocation(Bukkit.getWorld(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]), Float.parseFloat(data[4]), Float.parseFloat(data[5]));
	}

	public int x, y, z;

	public MutableBlockLocation(World world, int x, int y, int z, float yaw, float pitch) {
		super(world, yaw, pitch);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MutableBlockLocation(World world, int x, int y, int z){
		this(world, x, y, z, 0, 0);
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
	public MutableEntityLocation add(double x, double y, double z) {
		return new MutableEntityLocation(world, this.x + x, this.y + y, this.z + z, yaw, pitch);
	}

	@Override
	public MutableEntityLocation relative(double x, double y, double z) {
		return new MutableEntityLocation(world, x - this.x, y - this.y, z - this.z, yaw, pitch);
	}

	@Override
	public MutableEntityLocation asEntityLocation() {
		return new MutableEntityLocation(world, getEntityX(), getEntityY(), getEntityZ(), yaw, pitch);
	}

	@Override
	public ImmutableBlockLocation asImmutable() {
		return new ImmutableBlockLocation(world, x, y, z, yaw, pitch);
	}

}

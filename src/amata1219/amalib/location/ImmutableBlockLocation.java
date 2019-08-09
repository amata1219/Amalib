package amata1219.amalib.location;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class ImmutableBlockLocation extends ImmutableLocation implements BlockLocation {

	public static ImmutableBlockLocation deserialize(String text){
		String[] data = text.split(",");
		return new ImmutableBlockLocation(Bukkit.getWorld(data[0]), Integer.parseInt(data[1]),Integer.parseInt(data[2]), Integer.parseInt(data[3]), Float.parseFloat(data[4]), Float.parseFloat(data[5]));
	}

	public final int x, y, z;

	public ImmutableBlockLocation(World world, int x, int y, int z, float yaw, float pitch) {
		super(world, yaw, pitch);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public ImmutableBlockLocation(World world, int x, int y, int z) {
		this(world, x, y, z, 0, 0);
	}

	public ImmutableBlockLocation(org.bukkit.Location location){
		this(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getYaw(), location.getPitch());
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
	public ImmutableBlockLocation add(int x, int y, int z) {
		return new ImmutableBlockLocation(world, this.x + x, this.y + y, this.z + z, yaw, pitch);
	}

	@Override
	public ImmutableBlockLocation relative(int x, int y, int z) {
		return new ImmutableBlockLocation(world, x - this.x, y - this.y, z - this.z, yaw, pitch);
	}

	@Override
	public ImmutableEntityLocation middle(){
		return new ImmutableEntityLocation(world, x + (x >= 0 ? 0.5 : -0.5), y, z + (z >= 0 ? 0.5 : -0.5), yaw, pitch);
	}

	@Override
	public ImmutableEntityLocation asEntityLocation() {
		return new ImmutableEntityLocation(world, getEntityX(), getEntityY(), getEntityZ(), yaw, pitch);
	}

	@Override
	public MutableBlockLocation asMutable() {
		return new MutableBlockLocation(world, x, y, z, yaw, pitch);
	}

}

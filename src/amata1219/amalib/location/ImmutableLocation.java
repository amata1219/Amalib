package amata1219.amalib.location;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class ImmutableLocation implements Location {

	public static ImmutableLocation deserialize(String data){
		String[] coordinates = data.split(",");
		return new ImmutableLocation(Bukkit.getWorld(coordinates[0]), Double.parseDouble(coordinates[1]), Double.parseDouble(coordinates[2]), Double.parseDouble(coordinates[3]), Float.parseFloat(coordinates[4]), Float.parseFloat(coordinates[5]));
	}

	public final World world;
	public final double x, y, z;
	public final float yaw, pitch;

	public ImmutableLocation(World world, double x, double y, double z, float yaw, float pitch){
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public ImmutableLocation(World world, double x, double y, double z){
		this(world, x, y, z, 0f, 0f);
	}

	@Override
	public World getWorld() {
		return world;
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

	@Override
	public float getYaw() {
		return yaw;
	}

	@Override
	public float getPitch() {
		return pitch;
	}

	@Override
	public ImmutableLocation add(double x, double y, double z, float yaw, float pitch) {
		return new ImmutableLocation(world, x + this.x, y + this.y, z + this.z, yaw + this.yaw, pitch + this.pitch);
	}

	@Override
	public Location relative(double x, double y, double z, float yaw, float pitch) {
		return new ImmutableLocation(world, x - this.x, y - this.y, z - this.z, yaw - this.yaw, pitch - this.pitch);
	}

	public MutableLocation asMutable(){
		return new MutableLocation(world, x, y, z, yaw, pitch);
	}

}

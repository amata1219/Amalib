package amata1219.amalib.region;

import org.bukkit.Bukkit;
import org.bukkit.World;

import amata1219.amalib.location.ImmutableBlockLocation;
import amata1219.amalib.location.Location;
import amata1219.amalib.string.StringTemplate;
import amata1219.amalib.tuplet.Tuple;

public class Region {

	public final World world;
	public final ImmutableBlockLocation lesserBoundaryCorner, greaterBoundaryCorner;

	public static Region deserialize(String text){
		Tuple<ImmutableBlockLocation, ImmutableBlockLocation> corners = deserializeToCorners(text);
		return new Region(corners.first, corners.second);
	}

	public static Tuple<ImmutableBlockLocation, ImmutableBlockLocation> deserializeToCorners(String text){
		String[] data = text.split(",");
		World world = Bukkit.getWorld(data[0]);
		ImmutableBlockLocation lesserBoundaryCorner = new ImmutableBlockLocation(world, Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
		ImmutableBlockLocation greaterBoundaryCorner = new ImmutableBlockLocation(world, Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
		return new Tuple<>(lesserBoundaryCorner, greaterBoundaryCorner);
	}

	public Region(Location lesserBoundaryCorner, Location greaterBoundaryCorner){
		this(lesserBoundaryCorner.getWorld(), lesserBoundaryCorner.getBlockX(), lesserBoundaryCorner.getBlockY(), lesserBoundaryCorner.getBlockZ(),
				greaterBoundaryCorner.getBlockX(), greaterBoundaryCorner.getBlockY(), greaterBoundaryCorner.getBlockZ());
	}

	public Region(org.bukkit.Location lesserBoundaryCorner, org.bukkit.Location greaterBoundaryCorner){
		this(lesserBoundaryCorner.getWorld(), lesserBoundaryCorner.getBlockX(), lesserBoundaryCorner.getBlockY(), lesserBoundaryCorner.getBlockZ(),
				greaterBoundaryCorner.getBlockX(), greaterBoundaryCorner.getBlockY(), greaterBoundaryCorner.getBlockZ());
	}

	public Region(World world, int lesserBoundaryCornerX, int lesserBoundaryCornerY, int lesserBoundaryCornerZ,
					int greaterBoundaryCornerX, int greaterBoundaryCornerY, int greaterBoundaryCornerZ){

		this.world = world;

		lesserBoundaryCorner = new ImmutableBlockLocation(world,
				Math.min(lesserBoundaryCornerX, greaterBoundaryCornerX),
				Math.min(lesserBoundaryCornerY, greaterBoundaryCornerY),
				Math.min(lesserBoundaryCornerZ, greaterBoundaryCornerZ)
		);

		greaterBoundaryCorner = new ImmutableBlockLocation(world,
				Math.max(lesserBoundaryCornerX, greaterBoundaryCornerX),
				Math.max(lesserBoundaryCornerY, greaterBoundaryCornerY),
				Math.max(lesserBoundaryCornerZ, greaterBoundaryCornerZ)
		);
	}

	public boolean isIn(Location location){
		return isIn(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public boolean isIn(org.bukkit.Location location){
		return isIn(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public boolean isIn(World world, int x, int y, int z){
		return lesserBoundaryCorner.x <= x && x <= greaterBoundaryCorner.x
				&& lesserBoundaryCorner.y <= y && y <= greaterBoundaryCorner.y
				&& lesserBoundaryCorner.z <= z && z <= greaterBoundaryCorner.z
				&& world.equals(world);
	}

	public boolean isIn(World world, double x, double y, double z){
		return lesserBoundaryCorner.x <= x && x <= greaterBoundaryCorner.x
				&& lesserBoundaryCorner.y <= y && y <= greaterBoundaryCorner.y
				&& lesserBoundaryCorner.z <= z && z <= greaterBoundaryCorner.z
				&& world.equals(world);
	}

	public int getLength(){
		return greaterBoundaryCorner.x - lesserBoundaryCorner.x;
	}

	public int getHeight(){
		return greaterBoundaryCorner.y - lesserBoundaryCorner.y;
	}

	public int getWidth(){
		return greaterBoundaryCorner.z - lesserBoundaryCorner.z;
	}

	public int getArea(){
		return getLength() * getWidth();
	}

	public int getVolume(){
		return getArea() * getHeight();
	}

	public Region add(int x, int y, int z){
		return new Region(lesserBoundaryCorner.add(x, y, z), greaterBoundaryCorner.add(x, y, z));
	}

	public Region add(Location location){
		return add(location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public Region relative(int x, int y, int z){
		return new Region(lesserBoundaryCorner.relative(x, y, z), greaterBoundaryCorner.relative(x, y, z));
	}

	public Region relative(Location location){
		return relative(location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public String serialize(){
		return StringTemplate.apply("$0,$1,$2,$3,$4,$5,$6", world.getName(),
				lesserBoundaryCorner.x, lesserBoundaryCorner.y, lesserBoundaryCorner.z,
				greaterBoundaryCorner.x, greaterBoundaryCorner.y, greaterBoundaryCorner.z);
	}

}

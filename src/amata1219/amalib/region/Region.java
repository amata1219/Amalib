package amata1219.amalib.region;

import org.bukkit.Bukkit;
import org.bukkit.World;

import amata1219.amalib.location.ImmutableLocation;
import amata1219.amalib.location.Location;
import amata1219.amalib.string.StringTemplate;

public class Region {

	public final World world;
	public final ImmutableLocation lesserBoundaryCorner, greaterBoundaryCorner;

	public static Region deserialize(String data){
		String[] coordinates = data.split(",");
		World world = Bukkit.getWorld(coordinates[0]);
		ImmutableLocation lesserBoundaryCorner = new ImmutableLocation(world, Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[3]));
		ImmutableLocation greaterBoundaryCorner = new ImmutableLocation(world, Integer.parseInt(coordinates[4]), Integer.parseInt(coordinates[5]), Integer.parseInt(coordinates[6]));
		return new Region(lesserBoundaryCorner, greaterBoundaryCorner);
	}

	public Region(Location lesserBoundaryCorner, Location greaterBoundaryCorner){
		this(lesserBoundaryCorner.getWorld(), lesserBoundaryCorner.getIntX(), lesserBoundaryCorner.getIntY(), lesserBoundaryCorner.getIntZ(),
				greaterBoundaryCorner.getIntX(), greaterBoundaryCorner.getIntY(), greaterBoundaryCorner.getIntZ());
	}

	public Region(org.bukkit.Location lesserBoundaryCorner, org.bukkit.Location greaterBoundaryCorner){
		this(lesserBoundaryCorner.getWorld(), lesserBoundaryCorner.getBlockX(), lesserBoundaryCorner.getBlockY(), lesserBoundaryCorner.getBlockZ(),
				greaterBoundaryCorner.getBlockX(), greaterBoundaryCorner.getBlockY(), greaterBoundaryCorner.getBlockZ());
	}

	public Region(World world, int lesserBoundaryCornerX, int lesserBoundaryCornerY, int lesserBoundaryCornerZ,
					int greaterBoundaryCornerX, int greaterBoundaryCornerY, int greaterBoundaryCornerZ){

		this.world = world;

		lesserBoundaryCorner = new ImmutableLocation(world,
				Math.min(lesserBoundaryCornerX, greaterBoundaryCornerX),
				Math.min(lesserBoundaryCornerY, greaterBoundaryCornerY),
				Math.min(lesserBoundaryCornerZ, greaterBoundaryCornerZ)
		);

		greaterBoundaryCorner = new ImmutableLocation(world,
				Math.max(lesserBoundaryCornerX, greaterBoundaryCornerX),
				Math.max(lesserBoundaryCornerY, greaterBoundaryCornerY),
				Math.max(lesserBoundaryCornerZ, greaterBoundaryCornerZ)
		);
	}

	public boolean isIn(Location location){
		return isIn(location.getWorld(), location.getIntX(), location.getIntY(), location.getIntZ());
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
		return greaterBoundaryCorner.getIntX() - lesserBoundaryCorner.getIntX();
	}

	public int getHeight(){
		return greaterBoundaryCorner.getIntY() - lesserBoundaryCorner.getIntY();
	}

	public int getWidth(){
		return greaterBoundaryCorner.getIntZ() - lesserBoundaryCorner.getIntZ();
	}

	public int getArea(){
		return getLength() * getWidth();
	}

	public int getVolume(){
		return getArea() * getHeight();
	}

	public Region extend(int x, int y, int z){
		return new Region(lesserBoundaryCorner.add(Math.min(x, 0), Math.min(y, 0), Math.min(z, 0)), greaterBoundaryCorner.add(Math.max(x, 0), Math.max(y, 0), Math.max(z, 0)));
	}

	public Region add(int x, int y, int z){
		return new Region(lesserBoundaryCorner.add(x, y, z), greaterBoundaryCorner.add(x, y, z));
	}

	public Region add(Location location){
		return add(location.getIntX(), location.getIntY(), location.getIntZ());
	}

	public Region sub(int x, int y, int z){
		return new Region(lesserBoundaryCorner.add(x, y, z), greaterBoundaryCorner.add(x, y, z));
	}

	public Region sub(Location location){
		return add(-location.getIntX(), -location.getIntY(), -location.getIntZ());
	}

	public Region relative(int x, int y, int z){
		return new Region(lesserBoundaryCorner.relative(x, y, z), greaterBoundaryCorner.relative(x, y, z));
	}

	public Region relative(Location location){
		return relative(location.getIntX(), location.getIntY(), location.getIntZ());
	}

	public String serialize(){
		return StringTemplate.apply("$0,$1,$2,$3,$4,$5,$6", world.getName(), lesserBoundaryCorner.x, lesserBoundaryCorner.y, lesserBoundaryCorner.z, greaterBoundaryCorner.x, greaterBoundaryCorner.y, greaterBoundaryCorner.z);
	}

}

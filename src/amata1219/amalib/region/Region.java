package amata1219.amalib.region;

import org.bukkit.Location;
import org.bukkit.World;

import amata1219.amalib.location.ImmutableBlockLocation;
import amata1219.amalib.location.ImmutableLocation;
import amata1219.amalib.text.StringSplit;

public class Region {

	public final World world;
	public final ImmutableBlockLocation lesserBoundaryCorner, greaterBoundaryCorner;

	public static Region fromString(World world, String text){
		int[] coordinates = StringSplit.splitToIntArguments(text);
		return new Region(world, coordinates[0], coordinates[1], coordinates[2], coordinates[3], coordinates[4], coordinates[5]);
	}

	public Region(Location lesserBoundaryCorner, Location greaterBoundaryCorner){
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

	public boolean isIn(ImmutableLocation location){
		return isIn(location.world, location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public boolean isIn(Location location){
		return isIn(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public boolean isIn(World world, int x, int y, int z){
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

}

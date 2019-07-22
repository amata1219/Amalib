package amata1219.amalib.space;

import org.bukkit.Location;
import org.bukkit.World;

import amata1219.amalib.location.ImmutableBlockLocation;
import amata1219.amalib.location.ImmutableLocation;

public class Space {

	public final World world;
	public final ImmutableBlockLocation lesserBoundaryCorner, greaterBoundaryCorner;

	public Space(Location lesserBoundaryCorner, Location greaterBoundaryCorner){
		this(lesserBoundaryCorner.getWorld(), lesserBoundaryCorner.getBlockX(), lesserBoundaryCorner.getBlockY(), lesserBoundaryCorner.getBlockZ(),
				greaterBoundaryCorner.getBlockX(), greaterBoundaryCorner.getBlockY(), greaterBoundaryCorner.getBlockZ());
	}

	public Space(World world, int lesserBoundaryCornerX, int lesserBoundaryCornerY, int lesserBoundaryCornerZ,
					int greaterBoundaryCornerX, int greaterBoundaryCornerY, int greaterBoundaryCornerZ){

		this.world = world;

		lesserBoundaryCorner = ImmutableBlockLocation.at(world,
				Math.min(lesserBoundaryCornerX, greaterBoundaryCornerX),
				Math.min(lesserBoundaryCornerY, greaterBoundaryCornerY),
				Math.min(lesserBoundaryCornerZ, greaterBoundaryCornerZ)
		);

		greaterBoundaryCorner = ImmutableBlockLocation.at(world,
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

package amata1219.amalib.selection;

import org.bukkit.World;

import amata1219.amalib.location.ImmutableBlockLocation;
import amata1219.amalib.location.Location;
import amata1219.amalib.location.MutableBlockLocation;
import amata1219.amalib.string.StringTemplate;

public class RegionSelection {

	public final MutableBlockLocation boundaryCorner1 = new MutableBlockLocation(null, 0, 0, 0);
	public final MutableBlockLocation boundaryCorner2 = new MutableBlockLocation(null, 0, 0, 0);

	public void setWorld(World world){
		boundaryCorner1.world = boundaryCorner2.world = world;
	}

	public void setBoundaryCorner1(Location location){
		setBoundaryCorner1(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public void setBoundaryCorner1(org.bukkit.Location location){
		setBoundaryCorner1(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public void setBoundaryCorner1(World world, int x, int y, int z){
		boundaryCorner1.world = world;
		boundaryCorner1.x = x;
		boundaryCorner1.y = y;
		boundaryCorner1.z = z;
	}

	public void setBoundaryCorner2(Location location){
		setBoundaryCorner2(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public void setBoundaryCorner2(org.bukkit.Location location){
		setBoundaryCorner2(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public void setBoundaryCorner2(World world, int x, int y, int z){
		boundaryCorner2.world = world;
		boundaryCorner2.x = x;
		boundaryCorner2.y = y;
		boundaryCorner2.z = z;
	}

	public World getWorld(){
		return boundaryCorner1.world;
	}

	public ImmutableBlockLocation getLesserBoundaryCorner(){
		return new ImmutableBlockLocation(
			boundaryCorner1.world,
			Math.min(boundaryCorner1.x, boundaryCorner2.x),
			Math.min(boundaryCorner1.y, boundaryCorner2.y),
			Math.min(boundaryCorner1.z, boundaryCorner2.z)
		);
	}

	public ImmutableBlockLocation getGreaterBoundaryCorner(){
		return new ImmutableBlockLocation(
			boundaryCorner1.world,
			Math.max(boundaryCorner1.x, boundaryCorner2.x),
			Math.max(boundaryCorner1.y, boundaryCorner2.y),
			Math.max(boundaryCorner1.z, boundaryCorner2.z)
		);
	}

	@Override
	public String toString(){
		World world = getWorld();

		ImmutableBlockLocation lesser = getLesserBoundaryCorner();
		ImmutableBlockLocation greater = getLesserBoundaryCorner();

		//world,lesserX,lesserY,lesserZ,greaterX,greaterY,greaterZ
		return StringTemplate.apply("$0,$1,$2,$3,$4,$5,$6,$7", world != null ? world.getName() : "null",
				lesser.getBlockX(), lesser.getBlockY(), lesser.getBlockZ(),
				greater.getBlockX(), greater.getBlockY(), greater.getBlockZ());
	}

}

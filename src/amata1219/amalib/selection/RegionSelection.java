package amata1219.amalib.selection;

import org.bukkit.World;

import amata1219.amalib.location.ImmutableBlockLocation;
import amata1219.amalib.location.Location;
import amata1219.amalib.location.MutableBlockLocation;

public class RegionSelection {

	public final MutableBlockLocation boundaryCorner1 = new MutableBlockLocation(null, 0, 0, 0);
	public final MutableBlockLocation boundaryCorner2 = new MutableBlockLocation(null, 0, 0, 0);

	public void setWorld(World world){
		boundaryCorner1.world = boundaryCorner2.world = world;
	}

	public void setBoundaryCorner1(Location location){
		boundaryCorner1.world = location.getWorld();
		boundaryCorner1.x = location.getBlockX();
		boundaryCorner1.y = location.getBlockY();
		boundaryCorner1.z = location.getBlockZ();
	}

	public void setBoundaryCorner2(Location location){
		boundaryCorner2.world = location.getWorld();
		boundaryCorner2.x = location.getBlockX();
		boundaryCorner2.y = location.getBlockY();
		boundaryCorner2.z = location.getBlockZ();
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

}

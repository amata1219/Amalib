package amata1219.amalib.selection;

import org.bukkit.World;

import amata1219.amalib.location.ImmutableLocation;
import amata1219.amalib.location.Location;
import amata1219.amalib.location.MutableLocation;
import amata1219.amalib.region.Region;
import amata1219.amalib.string.StringTemplate;

public class RegionSelection {

	public final MutableLocation boundaryCorner1 = new MutableLocation(null, 0, 0, 0);
	public final MutableLocation boundaryCorner2 = new MutableLocation(null, 0, 0, 0);

	public void setWorld(World world){
		boundaryCorner1.world = boundaryCorner2.world = world;
	}

	public void setBoundaryCorner1(Location location){
		setBoundaryCorner1(location.getWorld(), location.getIntX(), location.getIntY(), location.getIntZ());
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
		setBoundaryCorner2(location.getWorld(), location.getIntX(), location.getIntY(), location.getIntZ());
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

	public ImmutableLocation getLesserBoundaryCorner(){
		return new ImmutableLocation(
			boundaryCorner1.world,
			Math.min(boundaryCorner1.x, boundaryCorner2.x),
			Math.min(boundaryCorner1.y, boundaryCorner2.y),
			Math.min(boundaryCorner1.z, boundaryCorner2.z)
		);
	}

	public ImmutableLocation getGreaterBoundaryCorner(){
		return new ImmutableLocation(
			boundaryCorner1.world,
			Math.max(boundaryCorner1.x, boundaryCorner2.x),
			Math.max(boundaryCorner1.y, boundaryCorner2.y),
			Math.max(boundaryCorner1.z, boundaryCorner2.z)
		);
	}

	public Region makeRegion(){
		return new Region(getLesserBoundaryCorner(), getGreaterBoundaryCorner());
	}

	@Override
	public String toString(){
		World world = getWorld();

		ImmutableLocation lesserBoundaryCorner = getLesserBoundaryCorner();
		ImmutableLocation greaterBoundaryCorner = getGreaterBoundaryCorner();

		return StringTemplate.apply("$0,$1,$2,$3,$4,$5,$6", world != null ? world.getName() : "null",
				lesserBoundaryCorner.getIntX(), lesserBoundaryCorner.getIntY(), lesserBoundaryCorner.getIntZ(),
				greaterBoundaryCorner.getIntX(), greaterBoundaryCorner.getIntY(), greaterBoundaryCorner.getIntZ());
	}

}

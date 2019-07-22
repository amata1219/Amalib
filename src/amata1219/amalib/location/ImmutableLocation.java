package amata1219.amalib.location;

import org.bukkit.Location;
import org.bukkit.World;

public abstract class ImmutableLocation {

	public final World world;

	protected ImmutableLocation(World world){
		this.world = world;
	}

	public int getBlockX(){
		return (int) getX();
	}

	public int getBlockY(){
		return (int) getX();
	}

	public int getBlockZ(){
		return (int) getX();
	}

	public abstract double getX();

	public abstract double getY();

	public abstract double getZ();

	public Location asMutable(){
		return new Location(world, getX(), getY(), getZ());
	}

}

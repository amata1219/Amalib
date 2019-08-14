package amata1219.amalib.location;

import org.bukkit.World;

import amata1219.amalib.region.Region;

public interface Location {

	World getWorld();

	int getBlockX();

	int getBlockY();

	int getBlockZ();

	default boolean isLocatedAt(int x, int y, int z){
		return x == getBlockX() && y == getBlockY() && z == getBlockZ();
	}

	default Location add(int x, int y, int z){
		return add((double) x, (double) y, (double) z);
	}

	default Location relative(int x, int y, int z){
		return relative((double) x, (double) y, (double) z);
	}

	default int distance2D(int x, int z){
		return (int) distance2D((double) x, (double) z);
	}

	default int distance3D(int x, int y, int z){
		return (int) distance3D((double) x, (double) y,(double) z);
	}

	double getEntityX();

	double getEntityY();

	double getEntityZ();

	default boolean isLocatedAt(double x, double y, double z){
		return x == getEntityX() && y == getEntityY() && z == getEntityZ();
	}

	Location add(double x, double y, double z);

	default Location add(Location location){
		return add(location.getEntityX(), location.getEntityY(), location.getEntityZ());
	}

	Location relative(double x, double y, double z);

	default Location relative(Location location){
		return relative(location.getEntityX(), location.getEntityY(), location.getEntityZ());
	}

	default double distance2D(double x, double z){
		return Math.sqrt(Math.pow(getEntityX() - x, 2) + Math.pow(getEntityZ() - z, 2));
	}

	default double distance2D(Location location){
		return distance2D(location.getEntityX(), location.getBlockZ());
	}

	default double distance3D(double x, double y, double z){
		return Math.sqrt(Math.pow(getEntityX() - x, 2) + Math.pow(getEntityY() - y, 2) + Math.pow(getEntityZ() - z, 2));
	}

	default double distance3D(Location location){
		return distance3D(location.getEntityX(), location.getEntityY(), location.getBlockZ());
	}

	default Location middle(){
		return add(getBlockX() >= 0 ? 0.5 : -0.5, 0, getBlockZ() >= 0 ? 0.5 : -0.5);
	}

	default Region add(Region region){
		Location lesserBoundaryCorner = region.lesserBoundaryCorner;
		Location greaterBoundaryCorner = region.greaterBoundaryCorner;
		return new Region(getWorld(), lesserBoundaryCorner.getBlockX() + getBlockX(), lesserBoundaryCorner.getBlockY() + getBlockY(), lesserBoundaryCorner.getBlockZ() + getBlockZ(),
				greaterBoundaryCorner.getBlockX() + getBlockX(), greaterBoundaryCorner.getBlockY() + getBlockY(), greaterBoundaryCorner.getBlockZ() + getBlockZ());
	}

	default Region relative(Region region){
		Location lesserBoundaryCorner = region.lesserBoundaryCorner;
		Location greaterBoundaryCorner = region.greaterBoundaryCorner;
		return new Region(getWorld(), lesserBoundaryCorner.getBlockX() - getBlockX(), lesserBoundaryCorner.getBlockY() - getBlockY(), lesserBoundaryCorner.getBlockZ() - getBlockZ(),
				greaterBoundaryCorner.getBlockX() - getBlockX(), greaterBoundaryCorner.getBlockY() - getBlockY(), greaterBoundaryCorner.getBlockZ() - getBlockZ());
	}

	float getYaw();

	float getPitch();

	boolean isSame(Location location);

	String serialize();

	default org.bukkit.Location asBukkitLocation(){
		return new org.bukkit.Location(getWorld(), getEntityX(), getEntityY(), getEntityZ(), getYaw(), getPitch());
	}

}

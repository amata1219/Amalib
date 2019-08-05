package amata1219.amalib.location;

import org.bukkit.World;

public interface Location {

	World getWorld();

	int getBlockX();

	int getBlockY();

	int getBlockZ();

	default boolean isLocatedAt(int x, int y, int z){
		return x == getBlockX() && y == getBlockY() && z == getBlockZ();
	}

	Location add(int x, int y, int z);

	Location relative(int x, int y, int z);

	double getEntityX();

	double getEntityY();

	double getEntityZ();

	default boolean isLocatedAt(double x, double y, double z){
		return x == getEntityX() && y == getEntityY() && z == getEntityZ();
	}

	Location add(double x, double y, double z);

	Location relative(double x, double y, double z);

	Location middle();

	float getYaw();

	float getPitch();

	boolean isSame(Location location);

	String serialize();

	default org.bukkit.Location asBukkitLocation(){
		return new org.bukkit.Location(getWorld(), getEntityX(), getEntityY(), getEntityZ());
	}

}

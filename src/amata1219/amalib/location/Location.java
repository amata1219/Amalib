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

	double getEntityX();

	double getEntityY();

	double getEntityZ();

	default boolean isLocatedAt(double x, double y, double z){
		return x == getEntityX() && y == getEntityY() && z == getEntityZ();
	}

	default org.bukkit.Location asBukkitLocation(){
		return new org.bukkit.Location(getWorld(), getEntityX(), getEntityY(), getEntityZ());
	}

}

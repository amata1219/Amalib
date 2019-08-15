package amata1219.amalib.location;

import org.bukkit.World;

import amata1219.amalib.string.StringTemplate;

public interface Location {

	World getWorld();

	double getX();

	double getY();

	double getZ();

	float getYaw();

	float getPitch();

	default int getIntX(){
		return (int) getX();
	}

	default int getIntY(){
		return (int) getY();
	}

	default int getIntZ(){
		return (int) getZ();
	}

	default boolean isAt(int x, int y, int z){
		return x == getIntX() && y == getIntY() && z == getIntZ();
	}

	default boolean isAt(double x, double y, double z){
		return x == getX() && y == getY() && z == getZ();
	}

	default Location add(int x, int y, int z){
		return add(x, y, z, 0f, 0f);
	}

	default Location add(int x, int y, int z, float yaw, float pitch){
		return add((double) x, (double) y, (double) z, yaw, pitch);
	}

	Location add(double x, double y, double z, float yaw, float pitch);

	default Location relative(int x, int y, int z){
		return relative(x, y, z, 0f, 0f);
	}

	default Location relative(int x, int y, int z, float yaw, float pitch){
		return relative((double) x, (double) y, (double) z, yaw, pitch);
	}

	Location relative(double x, double y, double z, float yaw, float pitch);

	default org.bukkit.Location asBukkit(){
		return new org.bukkit.Location(getWorld(), getX(), getY(), getZ(), getYaw(), getPitch());
	}

	default String serialize(){
		return StringTemplate.apply("$0,$1,$2,$3,$4,$5", getWorld().getName(), getX(), getY(), getZ(), getYaw(), getPitch());
	}

}

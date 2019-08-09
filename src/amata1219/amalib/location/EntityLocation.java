package amata1219.amalib.location;

import amata1219.amalib.string.StringTemplate;

public interface EntityLocation extends Location {

	default int getBlockX(){
		return (int) getEntityX();
	}

	default int getBlockY(){
		return (int) getEntityY();
	}

	default int getBlockZ(){
		return (int) getEntityZ();
	}

	@Override
	default EntityLocation add(int x, int y, int z) {
		return add((double) x, (double) y, (double) z);
	}

	@Override
	EntityLocation add(double x, double y, double z);

	default EntityLocation add(Location location){
		return add(location.getEntityX(), location.getEntityY(), location.getEntityZ());
	}

	@Override
	default EntityLocation relative(int x, int y, int z) {
		return relative((double) x, (double) y, (double) z);
	}

	@Override
	EntityLocation relative(double x, double y, double z);

	default EntityLocation relative(Location location){
		return relative(location.getEntityX(), location.getEntityY(), location.getEntityZ());
	}

	BlockLocation asBlockLocation();

	@Override
	default boolean isSame(Location location){
		return location.getEntityX() == getEntityX() && location.getEntityY() == getEntityY()
				&& location.getEntityZ() == getEntityZ() && location.getWorld().equals(getWorld())
				&& location.getYaw() == getYaw() && location.getPitch() == getPitch();
	}

	@Override
	default String serialize(){
		return StringTemplate.apply("$0,$1,$2,$3,$4,$5", getWorld().getName(), getEntityX(), getEntityY(), getEntityZ(), getYaw(), getPitch());
	}

}

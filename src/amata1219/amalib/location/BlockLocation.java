package amata1219.amalib.location;

import amata1219.amalib.string.StringTemplate;

public interface BlockLocation extends Location {

	@Override
	default double getEntityX(){
		return (double) getBlockX();
	}

	@Override
	default double getEntityY(){
		return (double) getBlockY();
	}

	@Override
	default double getEntityZ(){
		return (double) getBlockZ();
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

	EntityLocation asEntityLocation();

	@Override
	default boolean isSame(Location location){
		return location.getBlockX() == getBlockX() && location.getBlockY() == getBlockY()
				&& location.getBlockZ() == getBlockZ() && location.getWorld().equals(getWorld())
				&& location.getYaw() == getYaw() && location.getPitch() == getPitch();
	}

	@Override
	default String serialize(){
		return StringTemplate.apply("$0,$1,$2,$3,$4,$5", getWorld().getName(), getBlockX(), getBlockY(), getBlockZ(), getYaw(), getPitch());
	}

}

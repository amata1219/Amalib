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
	BlockLocation add(int x, int y, int z);

	@Override
	default BlockLocation add(double x, double y, double z) {
		return add((int) x, (int) y, (int) z);
	}

	default BlockLocation add(BlockLocation location){
		return add(location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	@Override
	BlockLocation relative(int x, int y, int z);

	@Override
	default BlockLocation relative(double x, double y, double z) {
		return relative((int) x, (int) y, (int) z);
	}

	default BlockLocation relative(BlockLocation location){
		return relative(location.getBlockX(), location.getBlockY(), location.getBlockZ());
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
		return StringTemplate.apply("$0,$1,$2,$3,$4,$5", getWorld(), getBlockX(), getBlockY(), getBlockZ(), getYaw(), getPitch());
	}

}

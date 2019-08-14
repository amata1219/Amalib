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

	EntityLocation asEntityLocation();

	@Override
	default boolean isSame(Location location){
		return location.getBlockX() == getBlockX() && location.getBlockY() == getBlockY() && location.getBlockZ() == getBlockZ()
				&& location.getYaw() == getYaw() && location.getPitch() == getPitch() && location.getWorld().equals(getWorld());
	}

	@Override
	default String serialize(){
		return StringTemplate.apply("$0,$1,$2,$3,$4,$5", getWorld().getName(), getBlockX(), getBlockY(), getBlockZ(), getYaw(), getPitch());
	}

}

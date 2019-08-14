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

	BlockLocation asBlockLocation();

	@Override
	default boolean isSame(Location location){
		return location.getEntityX() == getEntityX() && location.getEntityY() == getEntityY() && location.getEntityZ() == getEntityZ()
				&& location.getYaw() == getYaw() && location.getPitch() == getPitch() && location.getWorld().equals(getWorld());
	}

	@Override
	default String serialize(){
		return StringTemplate.apply("$0,$1,$2,$3,$4,$5", getWorld().getName(), getEntityX(), getEntityY(), getEntityZ(), getYaw(), getPitch());
	}

}

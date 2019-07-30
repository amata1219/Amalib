package amata1219.amalib.location;

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

	default boolean equals(EntityLocation location){
		return location.getEntityX() == getEntityX() && location.getEntityY() == getEntityY() && location.getEntityZ() == getEntityZ() && location.getWorld().equals(getWorld());
	}


}

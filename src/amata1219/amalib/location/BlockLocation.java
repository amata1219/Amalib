package amata1219.amalib.location;

public interface BlockLocation extends Location {

	default double getEntityX(){
		return (double) getBlockX();
	}

	default double getEntityY(){
		return (double) getBlockY();
	}

	default double getEntityZ(){
		return (double) getBlockZ();
	}

	BlockLocation add(int x, int y, int z);

	BlockLocation relative(BlockLocation another);

	default boolean equals(Location location){
		return location.getBlockX() == getBlockX() && location.getBlockY() == getBlockY() && location.getBlockZ() == getBlockZ() && location.getWorld().equals(getWorld());
	}

}

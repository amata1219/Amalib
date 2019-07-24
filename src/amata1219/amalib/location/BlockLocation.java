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

}

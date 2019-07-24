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

}

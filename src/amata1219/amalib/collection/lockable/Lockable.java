package amata1219.amalib.collection.lockable;

import java.util.UUID;

public interface Lockable {

	default void lock(UUID key){
		setLock(key, true);
	}

	default void unlock(UUID key){
		setLock(key, false);
	}

	boolean isLocked();

	void setLock(UUID key, boolean lock);

	boolean isValid(UUID key);

}

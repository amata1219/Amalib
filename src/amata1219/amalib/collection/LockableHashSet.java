package amata1219.amalib.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import java.util.function.Consumer;

public class LockableHashSet<E> extends HashSet<E> implements Lockable {

	private final UUID key = UUID.randomUUID();
	private boolean lock = true;

	public static <E> LockableHashSetLocker<E> of(){
		return new LockableHashSetLocker<E>(new LockableHashSet<E>());
	}

	public static <E> LockableHashSetLocker<E> of(Collection<E> elements){
		return new LockableHashSetLocker<E>(new LockableHashSet<E>());
	}

	public LockableHashSet(){
		super();
	}

	public LockableHashSet(boolean lock){
		super();
		this.lock = lock;
	}

	public LockableHashSet(Collection<E> elements){
		super(elements);
	}

	@Override
	public boolean isLocked() {
		return lock;
	}

	@Override
	public void setLock(UUID key, boolean lock){
		if(isValid(key))
			this.lock = lock;
		else
			throw new IllegalArgumentException("Invalid key.");
	}

	@Override
	public boolean isValid(UUID key) {
		return this.key.equals(key);
	}

	@Override
	public boolean add(E element){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.add(element);
	}

	@Override
	public boolean remove(Object object){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.remove(object);
	}

	@Override
	public void clear(){
		if(lock)
			throw new UnsupportedOperationException();
		else
			super.clear();
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();

		for(E element : this)
			builder.append(element.toString()).append(",");

		return builder.toString().substring(0, builder.length() - 1);
	}

	public static class LockableHashSetLocker<E> {

		public final LockableHashSet<E> set;

		public LockableHashSetLocker(LockableHashSet<E> set){
			this.set = set;
		}

		public void bypass(Consumer<LockableHashSet<E>> consumer){
			final boolean lock = set.isLocked();
			set.setLock(set.key, false);
			consumer.accept(set);
			set.setLock(set.key, lock);
		}

		public UUID getKey(){
			return set.key;
		}

	}

}

package amata1219.amalib.collection.lockable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LockableArrayList<E> extends ArrayList<E> implements Lockable {

	@SuppressWarnings("rawtypes")
	public static final LockableArrayListLocker EMPTY_LIST = of();

	private final UUID key = UUID.randomUUID();
	private boolean lock = true;

	@SuppressWarnings("unchecked")
	public static <E> LockableArrayListLocker<E> emptyList(){
		return (LockableArrayListLocker<E>) EMPTY_LIST;
	}

	public static <E> LockableArrayListLocker<E> of(){
		return new LockableArrayListLocker<E>(new LockableArrayList<E>());
	}

	public LockableArrayList(){

	}

	public LockableArrayList(boolean lock){
		this.lock = lock;
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
	public E set(int index, E element){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.set(index, element);
	}

	@Override
	public boolean add(E element){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.add(element);
	}

	@Override
	 public void add(int index, E element){
		if(lock)
			throw new UnsupportedOperationException();
		else
			super.add(index, element);
	}

	@Override
	 public E remove(int index){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.remove(index);
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
	public boolean addAll(Collection<? extends E> collection){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.addAll(collection);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.addAll(index, collection);
	}

	@Override
	public boolean removeAll(Collection<?> collection){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.removeAll(collection);
	}

	@Override
	public boolean retainAll(Collection<?> collection){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.retainAll(collection);
	}

	@Override
	public boolean removeIf(Predicate<? super E> filter){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.removeIf(filter);
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator){
		if(lock)
			throw new UnsupportedOperationException();
		else
			super.replaceAll(operator);
	}

	@Override
	public void sort(Comparator<? super E> comparator){
		if(lock)
			throw new UnsupportedOperationException();
		else
			super.sort(comparator);
	}

	public static class LockableArrayListLocker<E> {

		public final LockableArrayList<E> list;

		public LockableArrayListLocker(LockableArrayList<E> list){
			this.list = list;
		}

		public void bypass(Consumer<LockableArrayList<E>> consumer){
			final boolean lock = list.isLocked();
			list.setLock(list.key, false);
			consumer.accept(list);
			list.setLock(list.key, lock);
		}

		public UUID getKey(){
			return list.key;
		}

	}

}

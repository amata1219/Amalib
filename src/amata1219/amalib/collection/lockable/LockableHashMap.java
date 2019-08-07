package amata1219.amalib.collection.lockable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class LockableHashMap<K, V> extends HashMap<K, V> implements Lockable {

	private final UUID key = UUID.randomUUID();
	private boolean lock = true;

	public static <K, V> LockableHashMapLocker<K, V> of(){
		return new LockableHashMapLocker<K, V>(new LockableHashMap<K, V>());
	}

	public LockableHashMap(){

	}

	public LockableHashMap(boolean lock){
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
	public V put(K key, V value){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map){
		if(lock)
			throw new UnsupportedOperationException();
		else
			super.putAll(map);
	}

	@Override
	public V remove(Object key){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.remove(key);
	}

	@Override
	public void clear(){
		if(lock)
			throw new UnsupportedOperationException();
		else
			super.clear();
	}

	@Override
	public V putIfAbsent(K key, V value){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.putIfAbsent(key, value);
	}

	@Override
	public boolean remove(Object key, Object value){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.remove(key, value);
	}

	@Override
	public boolean replace(K key, V oldValue, V newValue){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.replace(key, oldValue, newValue);
	}

	@Override
	public V replace(K key, V value){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.replace(key, value);
	}

	@Override
	public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.computeIfAbsent(key, mappingFunction);
	}

	@Override
	public V computeIfPresent(K key,  BiFunction<? super K, ? super V, ? extends V> remappingFunction){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.computeIfPresent(key, remappingFunction);
	}

	@Override
	public V compute(K key,  BiFunction<? super K, ? super V, ? extends V> remappingFunction){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.compute(key, remappingFunction);
	}

	@Override
	public V merge(K key, V value,  BiFunction<? super V, ? super V, ? extends V> remappingFunction){
		if(lock)
			throw new UnsupportedOperationException();
		else
			return super.merge(key, value, remappingFunction);
	}

	@Override
	public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function){
		if(lock)
			throw new UnsupportedOperationException();
		else
			super.replaceAll(function);
	}

	public static class LockableHashMapLocker<K, V> {

		public final LockableHashMap<K, V> map;

		public LockableHashMapLocker(LockableHashMap<K, V> map){
			this.map = map;
		}

		public void bypass(Consumer<LockableHashMap<K, V>> consumer){
			final boolean lock = map.isLocked();
			map.setLock(map.key, false);
			consumer.accept(map);
			map.setLock(map.key, lock);
		}

		public UUID getKey(){
			return map.key;
		}

	}

}

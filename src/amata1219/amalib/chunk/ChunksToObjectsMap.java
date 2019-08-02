package amata1219.amalib.chunk;

import static amata1219.amalib.chunk.ChunkHashCalculator.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Chunk;

import amata1219.amalib.location.Location;

public class ChunksToObjectsMap<V> {

	public final Map<Long, List<V>> chunksToObjectsMap = new HashMap<>();

	public boolean containsChunkHash(Chunk chunk){
		return containsChunkHash(calculate(chunk));
	}

	public boolean containsChunkHash(Location location){
		return containsChunkHash(calculate(location));
	}

	public boolean containsChunkHash(org.bukkit.Location location){
		return containsChunkHash(calculate(location));
	}

	public boolean containsChunkHash(int x, int z){
		return containsChunkHash(calculate(x, z));
	}

	public boolean containsChunkHash(long chunkHash){
		return chunksToObjectsMap.containsKey(chunkHash);
	}

	public List<V> get(Chunk chunk){
		return get(calculate(chunk));
	}

	public List<V> get(Location location){
		return get(calculate(location));
	}

	public List<V> get(org.bukkit.Location location){
		return get(calculate(location));
	}

	public List<V> get(int x, int z){
		return get(calculate(x, z));
	}

	public List<V> get(long chunkHash){
		return chunksToObjectsMap.getOrDefault(chunkHash, Collections.emptyList());
	}

	public List<V> getAll(Location lesserBoundaryCorner, Location greaterBoundaryCorner){
		return getAll(calculateAll(lesserBoundaryCorner, greaterBoundaryCorner));
	}

	public List<V> getAll(org.bukkit.Location lesserBoundaryCorner, org.bukkit.Location greaterBoundaryCorner){
		return getAll(calculateAll(lesserBoundaryCorner, greaterBoundaryCorner));
	}

	public List<V> getAll(int lesserBoundaryX, int lesserBoundaryZ, int greaterBoundaryX, int greaterBoundaryZ){
		return getAll(calculateAll(lesserBoundaryX, lesserBoundaryZ, greaterBoundaryX, greaterBoundaryZ));
	}

	public List<V> getAll(List<Long> chunkHashes){
		List<V> list = new ArrayList<>();
		for(long chunkHash : chunkHashes)
			list.addAll(chunksToObjectsMap.get(chunkHash));
		return list;
	}

	public V put(Chunk chunk, V value){
		return put(calculate(chunk), value);
	}

	public V put(Location location, V value){
		return put(calculate(location), value);
	}

	public V put(org.bukkit.Location location, V value){
		return put(calculate(location), value);
	}

	public V put(int x, int z, V value){
		return put(calculate(x, z), value);
	}

	public V put(long chunkHash, V value){
		if(containsChunkHash(chunkHash))
			get(chunkHash).add(value);
		else
			chunksToObjectsMap.put(chunkHash, new ArrayList<>()).add(value);

		return value;
	}

	public V putAll(Location lesserBoundaryCorner, Location greaterBoundaryCorner, V value){
		return putAll(calculateAll(lesserBoundaryCorner, greaterBoundaryCorner), value);
	}

	public V putAll(org.bukkit.Location lesserBoundaryCorner, org.bukkit.Location greaterBoundaryCorner, V value){
		return putAll(calculateAll(lesserBoundaryCorner, greaterBoundaryCorner), value);
	}

	public V putAll(int lesserBoundaryX, int lesserBoundaryZ, int greaterBoundaryX, int greaterBoundaryZ, V value){
		return putAll(calculateAll(lesserBoundaryX, lesserBoundaryZ, greaterBoundaryX, greaterBoundaryZ), value);
	}

	public V putAll(List<Long> chunkHashes, V value){
		for(long chunkHash : chunkHashes)
			put(chunkHash, value);
		return value;
	}

	public V remove(Chunk chunk, V value){
		return remove(calculate(chunk), value);
	}

	public V remove(Location location, V value){
		return remove(calculate(location), value);
	}

	public V remove(org.bukkit.Location location, V value){
		return remove(calculate(location), value);
	}

	public V remove(int x, int z, V value){
		return remove(calculate(x, z), value);
	}

	public V remove(long chunkHash, V value){
		List<V> list = get(chunkHash);

		list.remove(value);

		if(list.isEmpty())
			chunksToObjectsMap.remove(chunkHash);

		return value;
	}

	public V removeAll(Location lesserBoundaryCorner, Location greaterBoundaryCorner, V value){
		return removeAll(calculateAll(lesserBoundaryCorner, greaterBoundaryCorner), value);
	}

	public V removeAll(org.bukkit.Location lesserBoundaryCorner, org.bukkit.Location greaterBoundaryCorner, V value){
		return removeAll(calculateAll(lesserBoundaryCorner, greaterBoundaryCorner), value);
	}

	public V removeAll(int lesserBoundaryX, int lesserBoundaryZ, int greaterBoundaryX, int greaterBoundaryZ, V value){
		return removeAll(calculateAll(lesserBoundaryX, lesserBoundaryZ, greaterBoundaryX, greaterBoundaryZ), value);
	}

	public V removeAll(List<Long> chunkHashes, V value){
		for(long chunkHash : chunkHashes)
			remove(chunkHash, value);
		return value;
	}

}

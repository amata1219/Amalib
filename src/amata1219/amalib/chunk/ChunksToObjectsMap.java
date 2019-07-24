package amata1219.amalib.chunk;

import static amata1219.amalib.chunk.ChunkHashCalculator.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Chunk;

import amata1219.amalib.location.Location;

public class ChunksToObjectsMap<T> {

	public final Map<Long, List<T>> chunksToObjectsMap = new HashMap<>();

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

	public List<T> get(Chunk chunk){
		return get(calculate(chunk));
	}

	public List<T> get(Location location){
		return get(calculate(location));
	}

	public List<T> get(org.bukkit.Location location){
		return get(calculate(location));
	}

	public List<T> get(int x, int z){
		return get(calculate(x, z));
	}

	public List<T> get(long chunkHash){
		return chunksToObjectsMap.getOrDefault(chunkHash, Collections.emptyList());
	}

	public List<T> getAll(Location lesserBoundaryCorner, Location greaterBoundaryCorner){
		return getAll(calculateAll(lesserBoundaryCorner, greaterBoundaryCorner));
	}

	public List<T> getAll(org.bukkit.Location lesserBoundaryCorner, org.bukkit.Location greaterBoundaryCorner){
		return getAll(calculateAll(lesserBoundaryCorner, greaterBoundaryCorner));
	}

	public List<T> getAll(int lesserBoundaryX, int lesserBoundaryZ, int greaterBoundaryX, int greaterBoundaryZ){
		return getAll(calculateAll(lesserBoundaryX, lesserBoundaryZ, greaterBoundaryX, greaterBoundaryZ));
	}

	public List<T> getAll(List<Long> chunkHashes){
		List<T> list = new ArrayList<>();
		for(long chunkHash : chunkHashes)
			list.addAll(chunksToObjectsMap.get(chunkHash));
		return list;
	}

	public T put(Chunk chunk, T value){
		return put(calculate(chunk), value);
	}

	public T put(Location location, T value){
		return put(calculate(location), value);
	}

	public T put(org.bukkit.Location location, T value){
		return put(calculate(location), value);
	}

	public T put(int x, int z, T value){
		return put(calculate(x, z), value);
	}

	public T put(long chunkHash, T value){
		if(containsChunkHash(chunkHash))
			get(chunkHash).add(value);
		else
			chunksToObjectsMap.put(chunkHash, new ArrayList<>()).add(value);

		return value;
	}

	public T putAll(Location lesserBoundaryCorner, Location greaterBoundaryCorner, T value){
		return putAll(calculateAll(lesserBoundaryCorner, greaterBoundaryCorner), value);
	}

	public T putAll(org.bukkit.Location lesserBoundaryCorner, org.bukkit.Location greaterBoundaryCorner, T value){
		return putAll(calculateAll(lesserBoundaryCorner, greaterBoundaryCorner), value);
	}

	public T putAll(int lesserBoundaryX, int lesserBoundaryZ, int greaterBoundaryX, int greaterBoundaryZ, T value){
		return putAll(calculateAll(lesserBoundaryX, lesserBoundaryZ, greaterBoundaryX, greaterBoundaryZ), value);
	}

	public T putAll(List<Long> chunkHashes, T value){
		for(long chunkHash : chunkHashes)
			put(chunkHash, value);
		return value;
	}

	public T remove(Chunk chunk, T value){
		return remove(calculate(chunk), value);
	}

	public T remove(Location location, T value){
		return remove(calculate(location), value);
	}

	public T remove(org.bukkit.Location location, T value){
		return remove(calculate(location), value);
	}

	public T remove(int x, int z, T value){
		return remove(calculate(x, z), value);
	}

	public T remove(long chunkHash, T value){
		get(chunkHash).remove(value);
		return value;
	}

}

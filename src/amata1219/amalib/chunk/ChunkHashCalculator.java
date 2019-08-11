package amata1219.amalib.chunk;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;

import amata1219.amalib.location.Location;

public class ChunkHashCalculator {

	public static long calculate(Chunk chunk){
		return calculate(chunk.getX(), chunk.getZ());
	}

	public static long calculate(Location location){
		return calculate(location.getBlockX(), location.getBlockZ());
	}

	public static long calculate(org.bukkit.Location location){
		return calculate(location.getBlockX(), location.getBlockZ());
	}

	public static long calculate(long x, long z){
		return ((x >> 4) << 32) ^ (z >> 4);
	}

	private static long calculateWithChunkLocation(long chunkX, long chunkZ){
		return (chunkX << 32) ^ chunkZ;
	}

	public static List<Long> calculateAll(Location lesserBoundaryCorner, Location greaterBoundaryCorner){
		return calculateAll(lesserBoundaryCorner.getBlockX(), lesserBoundaryCorner.getBlockZ(), greaterBoundaryCorner.getBlockX(), greaterBoundaryCorner.getBlockZ());
	}

	public static List<Long> calculateAll(org.bukkit.Location lesserBoundaryCorner, org.bukkit.Location greaterBoundaryCorner){
		return calculateAll(lesserBoundaryCorner.getBlockX(), lesserBoundaryCorner.getBlockZ(), greaterBoundaryCorner.getBlockX(), greaterBoundaryCorner.getBlockZ());
	}

	public static List<Long> calculateAll(long lesserBoundaryX, long lesserBoundaryZ, long greaterBoundaryX, long greaterBoundaryZ){
		long chunkX = lesserBoundaryX >> 4;
		long limitX = greaterBoundaryX >> 4;

		long chunkZ = lesserBoundaryZ >> 4;
		long limitZ = greaterBoundaryZ >> 4;

		long initialCapacity = (limitX - chunkX) * (limitZ - chunkZ);

		List<Long> list = new ArrayList<>((int) initialCapacity);

		for(; chunkX <= limitX; chunkX++)
			for(; chunkZ <= limitZ; chunkZ++)
				list.add(calculateWithChunkLocation(chunkX, chunkZ));

		return list;
	}

}

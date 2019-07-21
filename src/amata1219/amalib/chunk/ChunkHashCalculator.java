package amata1219.amalib.chunk;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;

import amata1219.amalib.location.ImmutableLocation;

public class ChunkHashCalculator {

	public static long calculate(Chunk chunk){
		return calculate(chunk.getX(), chunk.getZ());
	}

	public static long calculate(ImmutableLocation<?> location){
		return calculate(location.x.intValue(), location.z.intValue());
	}

	public static long calculate(Location location){
		return calculate(location.getBlockX(), location.getBlockZ());
	}

	public static long calculate(int x, int z){
		return (x << 28) ^ (z >> 4);
	}

	private static long calculateWithChunkLocation(int chunkX, int chunkZ){
		return (chunkX << 32) ^ chunkZ;
	}

	public static List<Long> calculateAll(ImmutableLocation<?> lesserBoundaryCorner, ImmutableLocation<?> greaterBoundaryCorner){
		return calculateAll(lesserBoundaryCorner.x.intValue(), lesserBoundaryCorner.z.intValue(), greaterBoundaryCorner.x.intValue(), greaterBoundaryCorner.z.intValue());
	}

	public static List<Long> calculateAll(Location lesserBoundaryCorner, Location greaterBoundaryCorner){
		return calculateAll(lesserBoundaryCorner.getBlockX(), lesserBoundaryCorner.getBlockZ(), greaterBoundaryCorner.getBlockX(), greaterBoundaryCorner.getBlockZ());
	}

	public static List<Long> calculateAll(int lesserBoundaryX, int lesserBoundaryZ, int greaterBoundaryX, int greaterBoundaryZ){
		int chunkX = lesserBoundaryX >> 4;
		int limitX = greaterBoundaryX >> 4;

		int chunkZ = lesserBoundaryZ >> 4;
		int limitZ = greaterBoundaryZ >> 4;

		List<Long> list = new ArrayList<>((limitX - chunkX) * (limitZ - chunkZ));

		for(; chunkX <= limitX; chunkX++)
			for(; chunkZ <= limitZ; chunkZ++)
				list.add(calculateWithChunkLocation(chunkX, chunkZ));

		return list;
	}

}

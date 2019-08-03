package amata1219.amalib.location;

import org.bukkit.World;

public class ImmutableBlockLocation extends ImmutableLocation implements BlockLocation {

	public final int x, y, z;

	public ImmutableBlockLocation(World world, int x, int y, int z) {
		super(world);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public int getBlockX() {
		return x;
	}

	@Override
	public int getBlockY() {
		return y;
	}

	@Override
	public int getBlockZ() {
		return z;
	}

	@Override
	public BlockLocation add(int x, int y, int z){
		return new ImmutableBlockLocation(world, this.x + x, this.y + y, this.z + z);
	}

	@Override
	public BlockLocation relative(BlockLocation another) {
		return new ImmutableBlockLocation(world, another.getBlockX() - x, another.getBlockY() - y, another.getBlockZ() - z);
	}

	@Override
	public MutableBlockLocation asMutable() {
		return new MutableBlockLocation(world, x, y, z);
	}

}

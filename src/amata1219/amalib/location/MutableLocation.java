package amata1219.amalib.location;

import org.bukkit.World;

public abstract class MutableLocation implements Location {

	public World world;

	protected MutableLocation(World world){
		this.world = world;
	}

	@Override
	public World getWorld(){
		return world;
	}

	public abstract ImmutableLocation asImmutable();

}

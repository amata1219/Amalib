package amata1219.amalib.location;

import org.bukkit.World;

public abstract class ImmutableLocation implements Location {

	public final World world;

	protected ImmutableLocation(World world){
		this.world = world;
	}

	@Override
	public World getWorld(){
		return world;
	}

	public abstract MutableLocation asMutable();

}

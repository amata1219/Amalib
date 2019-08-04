package amata1219.amalib.location;

import org.bukkit.World;

public abstract class MutableLocation implements Location {

	public World world;
	public float yaw, pitch;

	protected MutableLocation(World world, float yaw, float pitch){
		this.world = world;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	@Override
	public World getWorld(){
		return world;
	}

	@Override
	public float getYaw(){
		return yaw;
	}

	@Override
	public float getPitch(){
		return pitch;
	}

	public abstract ImmutableLocation asImmutable();

}

package amata1219.amalib.location;

import org.bukkit.World;

public abstract class ImmutableDirection<N extends Number, A extends Number> extends ImmutableLocation<N> {

	public final A yaw, pitch;

	public ImmutableDirection(World world, N x, N y, N z, A yaw, A pitch) {
		super(world, x, y, z);
		this.yaw = yaw;
		this.pitch = pitch;
	}

}

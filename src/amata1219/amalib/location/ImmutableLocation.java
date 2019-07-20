package amata1219.amalib.location;

import org.bukkit.World;

import amata1219.amalib.text.StringTemplate;

public abstract class ImmutableLocation<N extends Number> {

	public final World world;
	public final N x, y, z;

	public ImmutableLocation(World world, N x, N y, N z){
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public abstract ImmutableLocation<N> relative(N x, N y, N z);

	@Override
	public String toString(){
		return StringTemplate.format("$0,$1,$2,$3", world.getName(), x, y, z);
	}

}

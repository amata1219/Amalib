package amata1219.amalib.selection;

import amata1219.amalib.location.ImmutableBlockLocation;
import amata1219.amalib.location.MutableBlockLocation;

public class RegionSelection {

	public final MutableBlockLocation boundaryCorner1 = new MutableBlockLocation(null, 0, 0, 0);
	public final MutableBlockLocation boundaryCorner2 = new MutableBlockLocation(null, 0, 0, 0);

	public void applyBoundaryCorner1(MutableBlockLocation location){
		boundaryCorner1.world = location.world;
		boundaryCorner1.x = location.x;
		boundaryCorner1.y = location.y;
		boundaryCorner1.z = location.z;
	}

	public void applyBoundaryCorner2(MutableBlockLocation location){
		boundaryCorner2.world = location.world;
		boundaryCorner2.x = location.x;
		boundaryCorner2.y = location.y;
		boundaryCorner2.z = location.z;
	}

	public ImmutableBlockLocation getLesserBoundaryCorner(){
		return new ImmutableBlockLocation(
			boundaryCorner1.world,
			Math.min(boundaryCorner1.x, boundaryCorner2.x),
			Math.min(boundaryCorner1.y, boundaryCorner2.y),
			Math.min(boundaryCorner1.z, boundaryCorner2.z)
		);
	}

	public ImmutableBlockLocation getGreaterBoundaryCorner(){
		return new ImmutableBlockLocation(
			boundaryCorner1.world,
			Math.max(boundaryCorner1.x, boundaryCorner2.x),
			Math.max(boundaryCorner1.y, boundaryCorner2.y),
			Math.max(boundaryCorner1.z, boundaryCorner2.z)
		);
	}

}

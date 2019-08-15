package amata1219.amalib.region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.World;

import amata1219.amalib.location.ImmutableBlockLocation;
import amata1219.amalib.location.ImmutableEntityLocation;
import amata1219.amalib.location.OldLocation;

public class LocationOnBorderCollector {

	public static List<ImmutableEntityLocation> collect(Region region, int howManyPointsInBlock){
		if(howManyPointsInBlock <= 0)
			throw new IllegalArgumentException("Points in block must be one or more");

		region = region.extend(1, 0, 1);
		ImmutableBlockLocation lesserBoundaryCorner = region.lesserBoundaryCorner;
		OldLocation greaterBoundaryCorner = region.greaterBoundaryCorner;

		if(lesserBoundaryCorner.isSame(greaterBoundaryCorner)) return new ArrayList<>(Arrays.asList(lesserBoundaryCorner.asEntityLocation()));

		int divisor = howManyPointsInBlock - 1;

		int max = (region.getWidth() * divisor + region.getLength() * divisor) * 2 + 100;

		if(max <= 0) return new ArrayList<>(Arrays.asList(lesserBoundaryCorner.asEntityLocation()));

		//予想されるサイズを予め指定しておく
		List<ImmutableEntityLocation> locations = new ArrayList<>(max);

		World world = lesserBoundaryCorner.world;
		double y = lesserBoundaryCorner.getEntityY();

		//左上の境界角を始点とする
		ImmutableEntityLocation startLocation = new ImmutableEntityLocation(world, lesserBoundaryCorner.getEntityX(), y, greaterBoundaryCorner.getEntityZ());

		locations.add(startLocation);

		//一つ前の向き
		Direction direction = Direction.DOWN;

		//一つ前の座標
		ImmutableEntityLocation lastLocation = startLocation;

		//間隔を計算する
		double interval = 1D / howManyPointsInBlock;

		//始点に戻るまで処理を繰り返す
		label: while(locations.size() <= 1 || !(lastLocation = locations.get(locations.size() - 1)).equals(startLocation)){
			int x = lastLocation.getBlockX();
			int z = lastLocation.getBlockZ();

			double nextX = x + direction.xComponent;
			double nextZ = z + direction.zComponent;

			if(!region.isIn(world, nextX, y, nextZ)){
				//反時計回りに向きを変える
				switch(direction){
				case DOWN:
					direction = Direction.RIGHT;
					break;
				case RIGHT:
					direction = Direction.UP;
					break;
				case UP:
					direction = Direction.LEFT;
					break;
				case LEFT:
				default:
					break label;
				}
			}

			//現在座標のブロック内で間隔倍毎に座標を作成し追加する
			for(int count = 1; count <= howManyPointsInBlock; count++){
				double width = interval * count;
				locations.add(new ImmutableEntityLocation(world, x + direction.xComponent * width, y, z + direction.zComponent * width));

				//念の為の上限値
				if(max-- < 0) break label;
			}

		}

		return locations;
	}

	private static enum Direction {

		UP(0, 1),
		RIGHT(1, 0),
		DOWN(0, -1),
		LEFT(-1, 0);

		public final double xComponent, zComponent;

		private Direction(double xComponent, double zComponent){
			this.xComponent = xComponent;
			this.zComponent = zComponent;
		}

	}

}

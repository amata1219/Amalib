package amata1219.amalib.border;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;

import amata1219.amalib.location.ImmutableEntityLocation;
import amata1219.amalib.location.ImmutableLocation;
import amata1219.amalib.region.Region;

public class LocationOnBorderCollector {

	public static List<ImmutableEntityLocation> collect(Region region, int howManyPointsInBlock){
		if(howManyPointsInBlock <= 0)
			throw new IllegalArgumentException("Points in block must be one or more");

		int divisor = howManyPointsInBlock - 1;

		//予想されるサイズを予め指定しておく
		List<ImmutableEntityLocation> locations = new ArrayList<>((region.getWidth() * divisor + region.getLength() * divisor) * 2);

		ImmutableLocation lesserBoundaryCorner = region.lesserBoundaryCorner;

		World world = lesserBoundaryCorner.world;
		double y = lesserBoundaryCorner.getEntityY();

		//左上の境界角を始点とする
		ImmutableEntityLocation startLocation = new ImmutableEntityLocation(world, lesserBoundaryCorner.getEntityX(), y, region.greaterBoundaryCorner.getEntityZ());

		locations.add(startLocation);

		//一つ前の向き
		Direction direction = Direction.DOWN;

		//一つ前の座標
		ImmutableEntityLocation lastLocation = null;

		//始点に戻るまで処理を繰り返す
		label: while(locations.size() <= 1 || !(lastLocation = locations.get(locations.size() - 1)).equals(startLocation)){
			int x = lastLocation.getBlockX();
			int z = lastLocation.getBlockZ();

			ImmutableEntityLocation basedLocation;

			double nextX = x + direction.xComponent;
			double nextZ = z + direction.zComponent;

			//次の座標が領域内である時
			if(region.isIn(world, nextX, y, nextZ)){
				//基となる座標を作成する
				basedLocation = new ImmutableEntityLocation(world, nextX, y, nextZ);

			//領域外である時
			}else{
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
					direction = Direction.DOWN;
					break;
				default:
					break label;
				}

				//新しい向きの座標に更新する
				nextX = x + direction.xComponent;
				nextZ = z + direction.zComponent;

				//基となる座標を作成する
				basedLocation = new ImmutableEntityLocation(world, nextX, y, nextZ);
			}

			locations.add(basedLocation);

			//間隔を計算する
			double interval = 1D / howManyPointsInBlock;

			//現在座標のブロック内で間隔倍毎に座標を作成し追加する
			for(int count = 1; count < divisor; count++){
				double width = interval * count;
				locations.add(new ImmutableEntityLocation(world, nextX + direction.xComponent * width, y, nextZ + direction.zComponent * width));
			}
		}

		return locations;
	}

	public static enum Direction {

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

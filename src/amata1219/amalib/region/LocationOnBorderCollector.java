package amata1219.amalib.region;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;

import amata1219.amalib.location.ImmutableLocation;
import amata1219.amalib.location.Location;

public class LocationOnBorderCollector {

	public static List<ImmutableLocation> collect(Region region, int howManyPointsInBlock){
		if(howManyPointsInBlock <= 0)
			throw new IllegalArgumentException("Points in block must be one or more");

		region = region.extend(1, 0, 1);

		ImmutableLocation lesserBoundaryCorner = region.lesserBoundaryCorner;

		int divisor = howManyPointsInBlock - 1;

		//予想されるサイズを予め指定しておく
		List<ImmutableLocation> locations = new ArrayList<>((region.getWidth() * divisor + region.getLength() * divisor) * 2);

		World world = lesserBoundaryCorner.world;
		double y = lesserBoundaryCorner.y;

		//左上の境界角を始点とする
		ImmutableLocation startLocation = new ImmutableLocation(world, lesserBoundaryCorner.x, y, lesserBoundaryCorner.z);

		locations.add(startLocation);

		//一つ前の向き
		Direction direction = Direction.RIGHT;

		//一つ前の座標
		Location lastLocation = startLocation;

		//間隔を計算する
		double interval = 1d / divisor;

		//始点に戻るまで処理を繰り返す
		label: while(locations.size() <= 1 || !(lastLocation = locations.get(locations.size() - 1)).equals(startLocation)){
			int x = lastLocation.getIntX();
			int z = lastLocation.getIntZ();

			double nextX = x + direction.xComponent;
			double nextZ = z + direction.zComponent;

			if(!region.isIn(world, nextX, y, nextZ)){
				System.out.println("change");
				//反時計回りに向きを変える
				switch(direction){
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
			}

			//現在座標のブロック内で間隔倍毎に座標を作成し追加する
			for(int count = 1; count <= divisor; count++){
				double width = interval * count;
				locations.add(new ImmutableLocation(world, x + direction.xComponent * width, y, z + direction.zComponent * width));
			}

		}

		locations.remove(locations.size() - 1);

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

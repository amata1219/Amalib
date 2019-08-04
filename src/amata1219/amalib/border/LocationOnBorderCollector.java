package amata1219.amalib.border;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;

import amata1219.amalib.location.ImmutableEntityLocation;
import amata1219.amalib.location.ImmutableLocation;
import amata1219.amalib.region.Region;

public class LocationOnBorderCollector {

	public static void main(String[] $){
		//デバッグに当たりEntity/BlockLocation#equalsのworld比較をコメントアウトしている
		//Region#isIn(doubles)も同様

		Region region = Region.deserialize(null, "0,0,0,7,0,2");
		List<ImmutableEntityLocation> locations = collect(region, 4);
		System.out.println("LesserBoundaryCorner > " + region.lesserBoundaryCorner.asBukkitLocation().toString());
		System.out.println("GreaterBoundaryCorner > " + region.greaterBoundaryCorner.asBukkitLocation().toString());
		System.out.println("Interval > " + String.format("%.2f", 1D / 4));
		System.out.println("Size > " + locations.size());
		for(ImmutableEntityLocation location : locations){
			System.out.println("X > " + String.format("%.2f", location.getEntityX()) + " Z > " + String.format("%.2f", location.getEntityZ()));
		}
	}

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
					direction = Direction.DOWN;
					break;
				default:
					break label;
				}
			}

			//現在座標のブロック内で間隔倍毎に座標を作成し追加する
			for(int count = 1; count <= howManyPointsInBlock; count++){
				double width = interval * count;
				locations.add(new ImmutableEntityLocation(world, x + direction.xComponent * width, y, z + direction.zComponent * width));
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

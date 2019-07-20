package amata1219.amalib.range;

public class OpenEnd<N extends Number & Comparable<N>> extends RangeEnd<N> {

	public OpenEnd(N end) {
		super(end, false);
	}

	public static <N extends Number & Comparable<N>> OpenEnd<N> at(N end){
		return new OpenEnd<>(end);
	}


}

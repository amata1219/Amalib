package amata1219.amalib.range;

public class ClosedEnd<N extends Number & Comparable<N>> extends RangeEnd<N> {

	public ClosedEnd(N end){
		super(end, true);
	}

	public static <N extends Number & Comparable<N>> ClosedEnd<N> at(N end){
		return new ClosedEnd<>(end);
	}

}

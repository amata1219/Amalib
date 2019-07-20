package amata1219.amalib.range;

public class Range<N extends Number & Comparable<N>> {

	private final RangeEnd<N> left, right;

	public Range(RangeEnd<N> left, RangeEnd<N> right){
		if(!left.toPositiveInfinityContains(right.end) || !right.toNegativeInfinityContains(left.end))
			throw new IllegalArgumentException("Range is invalid.");

		this.left = left;
		this.right = right;
	}

	public static <N extends Number & Comparable<N>> Range<N> define(RangeEnd<N> left, RangeEnd<N> right){
		return new Range<>(left, right);
	}

	public boolean contains(N x){
		return left.toPositiveInfinityContains(x) && right.toNegativeInfinityContains(x);
	}

}

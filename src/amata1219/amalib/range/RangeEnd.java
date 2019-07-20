package amata1219.amalib.range;

public abstract class RangeEnd<N extends Number & Comparable<N>> {

	protected final N end;
	protected final boolean containsEqualSign;

	protected RangeEnd(N end, boolean containsEqualSign){
		this.end = end;
		this.containsEqualSign = containsEqualSign;
	}

	private static <N extends Number & Comparable<N>> int compare(N end, N x){
		return end.compareTo(x);
	}

	public boolean toPositiveInfinityContains(N x){
		int result = RangeEnd.compare(end, x);
		//end < x = -1, end == x = 0, end > x = 1;
		return containsEqualSign ? result <= 0 : result < 0;
	}

	public boolean toNegativeInfinityContains(N x){
		int result = RangeEnd.compare(end, x);
		return containsEqualSign ? result >= 0 : result > 0;
	}

}

package amata1219.amalib.tuplet;

import amata1219.amalib.string.StringTemplate;

public class Quintuple<F, S, T, FO, FI> {

	public final F first;
	public final S second;
	public final T third;
	public final FO fourth;
	public final FI fifth;

	public Quintuple(F first, S second, T third, FO fourth, FI fifth){
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
	}

	@Override
	public String toString(){
		return StringTemplate.apply("Tuple(first = $0, second = $1, third = $2, fourth = $3, fifth = $4)", first, second, third, fourth, fifth);
	}

}

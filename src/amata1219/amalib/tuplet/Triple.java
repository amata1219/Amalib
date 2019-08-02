package amata1219.amalib.tuplet;

import amata1219.amalib.string.StringTemplate;

public class Triple<F, S, T> {

	public final F first;
	public final S second;
	public final T third;

	public  Triple(F first, S second, T third){
		this.first = first;
		this.second = second;
		this.third = third;
	}

	@Override
	public String toString(){
		return StringTemplate.apply("Tuple(first = $0, second = $1, third = $2)", first, second, third);
	}

}

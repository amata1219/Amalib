package amata1219.amalib.tuplet;

import amata1219.amalib.string.StringTemplate;

public class Tuple<F, S> {

	public final F first;
	public final S second;

	public Tuple(F first, S second){
		this.first = first;
		this.second = second;
	}

	@Override
	public String toString(){
		return StringTemplate.apply("Tuple(first = $0, second = $1)", first, second);
	}

}

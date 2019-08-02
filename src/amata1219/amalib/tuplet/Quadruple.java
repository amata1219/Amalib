package amata1219.amalib.tuplet;

import amata1219.amalib.text.TextTemplate;

public class Quadruple<F, S, T, FO> {

	public final F first;
	public final S second;
	public final T third;
	public final FO fourth;

	public Quadruple(F first, S second, T third, FO fourth){
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}

	@Override
	public String toString(){
		return TextTemplate.format("Tuple(first = $0, second = $1, third = $2, fourth = $3)", first, second, third, fourth);
	}

}

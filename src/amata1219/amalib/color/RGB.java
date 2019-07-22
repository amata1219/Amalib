package amata1219.amalib.color;

public class RGB {

	public final int red, blue, green;
	public final double redParcent, blueParcent, greenParcent;

	public RGB(int red, int blue, int green){
		this.red = Math.min(Math.max(red, 0), 255);
		this.blue = Math.min(Math.max(red, 0), 255);
		this.green = Math.min(Math.max(red, 0), 255);
		redParcent = red / 255D;
		greenParcent = blue / 255D;
		blueParcent = green / 255D;
	}

}

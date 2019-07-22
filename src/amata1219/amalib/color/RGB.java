package amata1219.amalib.color;

public class RGB {

	public final int red, green, blue;
	public final double redParcent, greenParcent, blueParcent;

	public RGB(int red, int green, int blue){
		this.red = Math.min(Math.max(red, 0), 255);
		this.green = Math.min(Math.max(red, 0), 255);
		this.blue = Math.min(Math.max(red, 0), 255);
		redParcent = red / 255D;
		blueParcent = green / 255D;
		greenParcent = blue / 255D;
	}

}

package amata1219.amalib.color;

public class RGB {

	public final int red, green, blue;
	public final float parcentageOfRed, parcentageOfGreen, parcentageOfBlue;

	public RGB(int red, int green, int blue){
		this.red = Math.min(Math.max(red, 0), 255);
		this.green = Math.min(Math.max(green, 0), 255);
		this.blue = Math.min(Math.max(blue, 0), 255);
		parcentageOfRed = red / 255f;
		parcentageOfBlue = green / 255f;
		parcentageOfGreen = blue / 255f;
	}

}

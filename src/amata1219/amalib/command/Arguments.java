package amata1219.amalib.command;

import java.util.function.Consumer;

public class Arguments {

	public final String[] args;
	private int index;

	public Arguments(String[] args){
		this.args = args;
	}

	public int getIndex(){
		return index;
	}

	public String get(){
		int previousIndex = index - 1;
		return args.length > previousIndex ? args[previousIndex] : "";
	}

	public String getInRange(int start, int end){
		if(start > end)
			throw new IllegalArgumentException("Start must be lesser end");

		StringBuilder builder = new StringBuilder(args.length > start ? args[start] : "");
		for(; start <= Math.min(end, args.length - 1); start++)
			builder.append(" ").append(args[start]);

		return builder.toString();
	}

	public String next(){
		return args.length > index ? args[index++] : "";
	}

	public boolean hasNext(){
		return args.length > index;
	}

	public char nextChar(){
		return next().charAt(0);
	}

	public boolean hasNextChar(){
		return ArgumentType.CHAR.isType(this);
	}

	public boolean nextBoolean(){
		return Boolean.valueOf(next()).booleanValue();
	}

	public boolean hasNextBoolean(){
		return ArgumentType.BOOLEAN.isType(this);
	}

	public int nextInt(){
		return Integer.valueOf(next()).intValue();
	}

	public boolean hasNextInt(){
		return ArgumentType.INT.isType(this);
	}

	public long nextLong(){
		return Long.valueOf(next()).longValue();
	}

	public boolean hasNextLong(){
		return ArgumentType.LONG.isType(this);
	}

	public byte nextByte(){
		return Byte.valueOf(next()).byteValue();
	}

	public boolean hasNextByte(){
		return ArgumentType.BOOLEAN.isType(this);
	}

	public short nextShort(){
		return Short.valueOf(next());
	}

	public boolean hasNextShort(){
		return ArgumentType.SHORT.isType(this);
	}

	public float nextFloat(){
		return Float.valueOf(next());
	}

	public boolean hasNextFloat(){
		return ArgumentType.FLOAT.isType(this);
	}

	public double nextDouble(){
		return Double.valueOf(next());
	}

	public boolean hasNextDouble(){
		return ArgumentType.DOUBLE.isType(this);
	}

	private enum ArgumentType {

		CHAR((argument) -> {
			if(argument.length() != 1)
				throw new IllegalArgumentException("Argument length must be 1");
		}),
		BOOLEAN((argument) -> Boolean.parseBoolean(argument)),
		INT((argument) -> Integer.parseInt(argument)),
		LONG((argument) -> Long.parseLong(argument)),
		BYTE((argument) -> Byte.parseByte(argument)),
		SHORT((argument) -> Short.parseShort(argument)),
		FLOAT((argument) -> Float.parseFloat(argument)),
		DOUBLE((argument) -> Double.parseDouble(argument));

		private final Consumer<String> validator;

		private ArgumentType(Consumer<String> validator){
			this.validator = validator;
		}

		public boolean isType(Arguments arguments){
			String[] args = arguments.args;
			int index = arguments.index;
			try{
				validator.accept(args.length > index ? args[index] : "");
			}catch(Exception e){
				return false;
			}
			return true;
		}

	}

}

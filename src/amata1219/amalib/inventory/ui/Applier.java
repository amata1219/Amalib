package amata1219.amalib.inventory.ui;

public interface Applier<T> {

	default T apply(T value){
		define(value);
		return value;
	}

	void define(T value);
}

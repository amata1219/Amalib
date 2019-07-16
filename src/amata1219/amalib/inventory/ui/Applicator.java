package amata1219.amalib.inventory.ui;

public interface Applicator<T> {

	default T apply(T value){
		applicate(value);
		return value;
	}

	void applicate(T value);
}

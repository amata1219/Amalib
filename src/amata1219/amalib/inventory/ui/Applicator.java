package amata1219.amalib.inventory.ui;

public interface Applicator<T> {

	default T applicate(T value){
		apply(value);
		return value;
	}

	void apply(T value);
}

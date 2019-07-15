package amata1219.amalib.inventory.ui;

public interface Applier<T> {

	default T apply(T value){
		action(value);
		return value;
	}

	void action(T value);
}

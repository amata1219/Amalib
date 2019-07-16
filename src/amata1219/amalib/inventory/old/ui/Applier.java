package amata1219.amalib.inventory.old.ui;

public interface Applier<T> {

	default T apply(T value){
		action(value);
		return value;
	}

	void action(T value);
}

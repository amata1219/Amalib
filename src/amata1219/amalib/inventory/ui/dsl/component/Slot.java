package amata1219.amalib.inventory.ui.dsl.component;

import java.util.function.Consumer;

import org.bukkit.event.inventory.InventoryClickEvent;
import amata1219.amalib.inventory.ui.dsl.event.UIClickEvent;

public class Slot {

	public final Icon icon = new Icon();
	public Consumer<UIClickEvent> actionOnClick;

	public void apply(Consumer<Icon> applier){
		applier.accept(icon);
	}

	public void onClick(Consumer<UIClickEvent> action){
		actionOnClick = action;
	}

	public void fire(InventoryClickEvent event){
		if(actionOnClick != null)
			actionOnClick.accept(new UIClickEvent(event));
	}

}

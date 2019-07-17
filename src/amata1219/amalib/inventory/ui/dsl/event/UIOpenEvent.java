package amata1219.amalib.inventory.ui.dsl.event;

import org.bukkit.event.inventory.InventoryOpenEvent;

public class UIOpenEvent extends UIEvent {

	public UIOpenEvent(InventoryOpenEvent event){
		super(event.getPlayer(), event);
	}

}

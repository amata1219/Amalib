package amata1219.amalib.inventory.ui.dsl.event;

import org.bukkit.event.inventory.InventoryCloseEvent;

public class UICloseEvent extends UIEvent {

	public UICloseEvent(InventoryCloseEvent event){
		super(event.getPlayer(), event);
	}

}

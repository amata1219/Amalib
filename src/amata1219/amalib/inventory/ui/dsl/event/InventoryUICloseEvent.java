package amata1219.amalib.inventory.ui.dsl.event;

import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryUICloseEvent extends InventoryEvent {

	public InventoryUICloseEvent(InventoryCloseEvent event){
		super(event.getPlayer(), event);
	}

}

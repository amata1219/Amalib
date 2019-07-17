package amata1219.amalib.inventory.ui.dsl.event;

import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryUIOpenEvent extends InventoryEvent {

	public InventoryUIOpenEvent(InventoryOpenEvent event){
		super(event.getPlayer(), event);
	}

}

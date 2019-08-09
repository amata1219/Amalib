package amata1219.amalib.inventory.ui.listener;

import org.bukkit.event.inventory.InventoryCloseEvent;

import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;

public class CloseEvent extends UIEvent {

	public CloseEvent(InventoryLayout layout, InventoryCloseEvent event){
		super(layout, event.getPlayer(), event);
	}

}

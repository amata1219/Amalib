package amata1219.amalib.inventory.ui.dsl.event;

import org.bukkit.event.inventory.InventoryCloseEvent;

import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;

public class UICloseEvent extends UIEvent {

	public UICloseEvent(InventoryLayout layout, InventoryCloseEvent event){
		super(layout, event.getPlayer(), event);
	}

}

package amata1219.amalib.inventory.ui.dsl.event;

import org.bukkit.event.inventory.InventoryOpenEvent;

import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;

public class UIOpenEvent extends UIEvent {

	public UIOpenEvent(InventoryLayout layout, InventoryOpenEvent event){
		super(layout, event.getPlayer(), event);
	}

}

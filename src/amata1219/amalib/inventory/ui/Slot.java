package amata1219.amalib.inventory.ui;

import org.bukkit.event.inventory.InventoryClickEvent;

public class Slot {

	public final Applier<Icon> iconApplier;
	public final Applier<InventoryClickEvent> actionOnClick;

	public Slot(Applier<Icon> iconApplier){
		this.iconApplier = iconApplier;
	}

}

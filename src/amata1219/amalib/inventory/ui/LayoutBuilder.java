package amata1219.amalib.inventory.ui;

import org.bukkit.event.inventory.InventoryType;

public class LayoutBuilder {

	public final InventoryLayout layout;

	public LayoutBuilder(InventoryUI ui, InventoryType type, int lines, Applier<InventoryLayout> build){
		layout = build.apply(new InventoryLayout(ui, type, lines));
	}

	public static InventoryLayout build(InventoryUI ui, InventoryType type, int lines, Applier<InventoryLayout> build){
		return build.apply(new InventoryLayout(ui, type, lines));
	}

}

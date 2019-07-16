package amata1219.amalib.inventory.ui;

import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Slot {

	private Applicator<Icon> iconApplicator;
	private Consumer<InventoryClickEvent> actionOnClick;

	public Icon buildIcon(){
		return iconApplicator.applicate(new Icon());
	}

	public void icon(Applicator<Icon> applicator){
		iconApplicator = applicator;
	}

	public void icon(Material iconMaterial, Applicator<Icon> applicator){
		icon((icon) -> {
			icon.material = iconMaterial;
			applicator.applicate(icon);
		});
	}

	public void icon(ItemStack item, Applicator<Icon> applicator){
		icon((icon) -> {
			icon.basedItem = item;
			applicator.applicate(icon);
		});
	}

	public void onClick(Consumer<InventoryClickEvent> action){
		actionOnClick = action;
	}

	public void fire(InventoryClickEvent event){
		actionOnClick.accept(event);
	}

}

package amata1219.amalib.inventory.ui.dsl.component;

import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import amata1219.amalib.inventory.ui.Applier;
import amata1219.amalib.inventory.ui.dsl.event.UIClickEvent;

public class Slot {

	private Applier<Icon> iconApplier;
	private Consumer<UIClickEvent> actionOnClick = (event) -> {};

	public Icon buildIcon(){
		return iconApplier.apply(new Icon());
	}

	public void icon(Applier<Icon> applier){
		iconApplier = applier;
	}

	public void icon(Material iconMaterial, Applier<Icon> applier){
		icon((icon) -> {
			icon.material = iconMaterial;
			applier.apply(icon);
		});
	}

	public void icon(ItemStack item, Applier<Icon> applier){
		icon((icon) -> {
			icon.basedItemStack = item;
			applier.apply(icon);
		});
	}

	public void onClick(Consumer<UIClickEvent> action){
		actionOnClick = action;
	}

	public void fire(InventoryClickEvent event){
		fire(new UIClickEvent(event));
	}

	public void fire(UIClickEvent event){
		actionOnClick.accept(event);
	}

}

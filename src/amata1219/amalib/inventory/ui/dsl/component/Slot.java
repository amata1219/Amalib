package amata1219.amalib.inventory.ui.dsl.component;

import java.util.function.Consumer;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import amata1219.amalib.inventory.ui.Applier;
import amata1219.amalib.inventory.ui.dsl.event.UIClickEvent;

public class Slot {

	private Applier<Icon> iconApplier = (icon) -> {};
	public boolean editable;
	private Consumer<UIClickEvent> actionOnClick = (event) -> {};

	public Icon buildIcon(){
		return iconApplier.apply(new Icon());
	}

	public void icon(ItemStack basedItemStack, Applier<Icon> iconApplier){
		this.iconApplier = (icon) -> {
			icon.basedItemStack = basedItemStack;
			iconApplier.apply(icon);
		};
	}

	public void icon(Material material, Applier<Icon> iconApplier){
		this.iconApplier = (icon) -> {
			icon.material = material;
			iconApplier.apply(icon);
		};
	}

	public void icon(Applier<Icon> iconApplier){
		this.iconApplier = iconApplier;
	}

	public void onClick(Consumer<UIClickEvent> action){
		Validate.notNull(action, "Action can not be null");
		actionOnClick = action;
	}

	public void fire(UIClickEvent event){
		actionOnClick.accept(event);
	}

}

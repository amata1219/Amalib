package amata1219.amalib.inventory.ui.dsl.component;

import java.util.function.Consumer;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import amata1219.amalib.inventory.ui.Apply;
import amata1219.amalib.inventory.ui.listener.ClickEvent;
import amata1219.amalib.schedule.Async;

public class Slot {

	//アイコンに適用する処理
	private Apply<Icon> iconApplier = (icon) -> {};

	//編集可能なスロットかどうか
	public boolean editable;

	//非同期で処理を実行するかどうか
	public boolean async;

	//クリック処理
	private Consumer<ClickEvent> actionOnClick = (event) -> {};

	public Icon buildIcon(){
		return iconApplier.apply(new Icon());
	}

	public void icon(ItemStack basedItemStack, Apply<Icon> iconApplier){
		this.iconApplier = (icon) -> {
			icon.basedItemStack = basedItemStack;
			iconApplier.apply(icon);
		};
	}

	public void icon(Material material, Apply<Icon> iconApplier){
		this.iconApplier = (icon) -> {
			icon.material = material;
			iconApplier.apply(icon);
		};
	}

	public void icon(Apply<Icon> iconApplier){
		this.iconApplier = iconApplier;
	}

	public void onClick(Consumer<ClickEvent> action){
		Validate.notNull(action, "Action can not be null");
		actionOnClick = action;
	}

	public void fire(ClickEvent event){
		if(async)
			Async.define(() -> actionOnClick.accept(event)).execute();
		else
			actionOnClick.accept(event);
	}

}

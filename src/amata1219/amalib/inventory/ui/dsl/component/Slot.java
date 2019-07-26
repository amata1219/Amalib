package amata1219.amalib.inventory.ui.dsl.component;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import amata1219.amalib.inventory.ui.Applier;
import amata1219.amalib.inventory.ui.dsl.event.UIClickEvent;
import amata1219.amalib.schedule.Async;

public class Slot {

	//アイコンに適用する処理
	private Applier<Icon> iconApplier = (icon) -> {};

	//編集可能なスロットかどうか
	public boolean editable;

	//非同期で処理を実行するかどうか
	public boolean async;

	//クリックイベントのフィルター(trueであれば通過出来る)
	private Predicate<UIClickEvent> filterOnClick = (event) -> { return true; };

	//クリック処理
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

	public void filterOnClick(Predicate<UIClickEvent> filter){
		Validate.notNull(filter, "Filter can not be null");
		filterOnClick = filter;
	}

	public void onClick(Consumer<UIClickEvent> action){
		Validate.notNull(action, "Action can not be null");
		actionOnClick = action;
	}

	public void fire(UIClickEvent event){
		if(filterOnClick.test(event))
			if(async)
				Async.define(() -> actionOnClick.accept(event)).execute();
			else
				actionOnClick.accept(event);
	}

}

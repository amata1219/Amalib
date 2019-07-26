package amata1219.amalib.inventory.ui.dsl.component;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import amata1219.amalib.inventory.ui.Applier;
import amata1219.amalib.inventory.ui.dsl.InventoryUI;
import amata1219.amalib.inventory.ui.dsl.event.UIClickEvent;
import amata1219.amalib.inventory.ui.dsl.event.UICloseEvent;
import amata1219.amalib.inventory.ui.dsl.event.UIOpenEvent;
import amata1219.amalib.inventory.ui.option.InventoryOption;
import amata1219.amalib.schedule.Async;

public class InventoryLayout {

	//インベントリを開いているプレイヤー
	public final Player player;

	public final InventoryUI ui;
	public final InventoryOption option;

	//インベントリのタイトル
	public String title;

	//各スロット
	private final HashMap<Integer, Slot> slots = new HashMap<>();

	//デフォルトのスロットに適用する処理
	private Applier<Slot> defaultSlot = (slot) -> {};

	//非同期でクリック処理を実行するかどうか
	public boolean asynchronouslyRunActionOnClick;

	//クリックイベントのフィルター
	private Predicate<UIClickEvent> filterOnClick = (event) -> { return true; };

	//クリック処理
	private Consumer<UIClickEvent> actionOnClick = (event) -> {};

	//非同期でオープン処理を実行するかどうか
	public boolean asynchronouslyRunActionOnOpen;

	//オープンイベントのフィルター
	private Predicate<UIOpenEvent> filterOnOpen = (event) -> { return true; };

	//オープン処理
	private Consumer<UIOpenEvent> actionOnOpen = (event) -> {};

	//非同期でクローズ処理を実行するかどうか
	public boolean asynchronouslyRunActionOnClose;

	//クローズイベントのフィルター
	private Predicate<UICloseEvent> filterOnClose = (event) -> { return true; };

	//クローズ処理
	private Consumer<UICloseEvent> actionOnClose = (event) -> {};

	public InventoryLayout(Player player, InventoryUI ui, InventoryOption option){
		this.player = player;
		this.ui = ui;
		this.option = option;
	}

	public Inventory buildInventory(){
		Inventory inventory = createInventory(ui, option, title);

		for(int slotIndex = 0; slotIndex < inventory.getSize(); slotIndex++)
			inventory.setItem(slotIndex, getSlotAt(slotIndex).buildIcon().toItemStack());

		return inventory;
	}

	public Slot getSlotAt(int slotIndex){
		return slots.containsKey(slotIndex) ? slots.get(slotIndex) : defaultSlot.apply(new Slot());
	}

	public void defaultSlot(Applier<Slot> slotApplier){
		Validate.notNull(slotApplier, "Slot applier can not be null");
		defaultSlot = slotApplier;
	}

	public void put(Applier<Slot> slotApplier, IntStream range){
		put(slotApplier, range.toArray());
	}

	public void put(Applier<Slot> slotApplier, int... slotIndexes){
		for(int slotIndex : slotIndexes)
			slots.put(slotIndex, slotApplier.apply(new Slot()));
	}

	public void remove(IntStream range){
		remove(range.toArray());
	}

	public void remove(int... slotIndexes){
		for(int slotIndex : slotIndexes)
			slots.remove(slotIndex);
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
			if(asynchronouslyRunActionOnClick)
				Async.define(() -> actionOnClick.accept(event));
			else
				actionOnClick.accept(event);
	}

	public void filterOnOpen(Predicate<UIOpenEvent> filter){
		Validate.notNull(filter, "Filter can not be null");
		filterOnOpen = filter;
	}

	public void onOpen(Consumer<UIOpenEvent> action){
		Validate.notNull(action, "Action can not be null");
		actionOnOpen = action;
	}

	public void fire(UIOpenEvent event){
		if(filterOnOpen.test(event))
			if(asynchronouslyRunActionOnOpen)
				Async.define(() -> actionOnOpen.accept(event));
			else
				actionOnOpen.accept(event);
	}

	public void filterOnClose(Predicate<UICloseEvent> filter){
		Validate.notNull(filter, "Filter can not be null");
		filterOnClose = filter;
	}

	public void onClose(Consumer<UICloseEvent> action){
		Validate.notNull(action, "Action can not be null");
		actionOnClose = action;
	}

	public void fire(UICloseEvent event){
		if(filterOnClose.test(event))
			if(asynchronouslyRunActionOnClose)
				Async.define(() -> actionOnClose.accept(event));
			else
				actionOnClose.accept(event);
	}

	private Inventory createInventory(InventoryHolder holder, InventoryOption option, String title){
		int size = option.size;
		InventoryType type = option.type;

		if(option.type == null)
			if(title != null)
				return Bukkit.createInventory(holder, size, title);
			else
				return Bukkit.createInventory(holder, size);
		else
			if(title != null)
				return Bukkit.createInventory(holder, type, title);
			else
				return Bukkit.createInventory(holder, type);
	}

}

package amata1219.amalib.inventory.ui.dsl.event;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public abstract class InventoryEvent {

	public final Player player;
	public final List<Player> viewers;
	public final Inventory inventory;
	public final InventoryView view;
	public final String eventName;

	public InventoryEvent(HumanEntity human, org.bukkit.event.inventory.InventoryEvent event){
		player = (Player) human;
		viewers = event.getViewers()
				.stream()
				.map(Player.class::cast)
				.collect(Collectors.toList());
		inventory = event.getInventory();
		view = event.getView();
		eventName = event.getEventName();
	}

}

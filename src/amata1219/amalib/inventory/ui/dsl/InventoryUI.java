package amata1219.amalib.inventory.ui.dsl;

import java.util.function.Function;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import amata1219.amalib.inventory.ui.Applier;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;
import amata1219.amalib.inventory.ui.option.InventoryLine;
import amata1219.amalib.inventory.ui.option.InventoryOption;

public interface InventoryUI extends InventoryHolder {

	Function<Player, InventoryLayout> layout();

	default void openInventory(Player player){
		player.openInventory(layout().apply(player).buildInventory());
	}

	@Override
	default Inventory getInventory(){
		throw new UnsupportedOperationException("Use InventoryUI#layout().buildInventory() instead.");
	}

	default Function<Player, InventoryLayout> build(InventoryOption option, Applier<InventoryLayout> applier){
		return LayoutBuilder.build(this, option, applier);
	}

	default Function<Player, InventoryLayout> build(InventoryType type, Applier<InventoryLayout> applier){
		return build(new InventoryOption(null, type), applier);
	}

	default Function<Player, InventoryLayout> build(InventoryLine line, Applier<InventoryLayout> applier){
		return build(new InventoryOption(line, null), applier);
	}

}

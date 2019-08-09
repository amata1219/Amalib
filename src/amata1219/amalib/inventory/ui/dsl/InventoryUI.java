package amata1219.amalib.inventory.ui.dsl;

import java.util.function.Function;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import amata1219.amalib.inventory.ui.Apply;
import amata1219.amalib.inventory.ui.InventoryLine;
import amata1219.amalib.inventory.ui.InventoryOption;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;

public interface InventoryUI extends InventoryHolder {

	Function<Player, InventoryLayout> layout();

	default void openInventory(Player player){
		player.openInventory(layout().apply(player).buildInventory());
	}

	@Override
	default Inventory getInventory(){
		throw new UnsupportedOperationException("Use InventoryUI#openInvnetory(Player) instead.");
	}

	default Function<Player, InventoryLayout> build(InventoryOption option, Apply<InventoryLayout> applier){
		return LayoutBuilder.build(this, option, applier);
	}

	default Function<Player, InventoryLayout> build(InventoryType type, Apply<InventoryLayout> applier){
		return build(new InventoryOption(null, type), applier);
	}

	default Function<Player, InventoryLayout> build(InventoryLine line, Apply<InventoryLayout> applier){
		return build(new InventoryOption(line, null), applier);
	}

	default Function<Player, InventoryLayout> build(int size, Apply<InventoryLayout> applier){
		return build(InventoryLine.necessaryInventoryLine(size), applier);
	}

}

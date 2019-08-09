package amata1219.amalib.inventory.ui.dsl;

import java.util.function.Function;

import org.bukkit.entity.Player;

import amata1219.amalib.inventory.ui.Apply;
import amata1219.amalib.inventory.ui.InventoryOption;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;

public class LayoutBuilder {

	public static Function<Player, InventoryLayout> build(InventoryUI ui, InventoryOption option, Apply<InventoryLayout> applier){
		return (player) -> applier.apply(new InventoryLayout(player, ui, option));
	}

}

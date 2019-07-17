package amata1219.amalib.inventory.ui.dsl;

import java.util.function.Function;

import org.bukkit.entity.Player;

import amata1219.amalib.inventory.ui.Applier;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;
import amata1219.amalib.inventory.ui.option.InventoryOption;

public class LayoutBuilder {

	public static Function<Player, InventoryLayout> build(InventoryUI ui, InventoryOption option, Applier<InventoryLayout> applier){
		return (player) -> applier.apply(new InventoryLayout(player, ui, option));
	}

}

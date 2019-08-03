package amata1219.amalib.inventory.ui;

import java.util.function.Function;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import amata1219.amalib.inventory.ui.dsl.InventoryUI;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;
import amata1219.amalib.inventory.ui.option.InventoryLine;
import amata1219.amalib.string.StringTemplate;

public class SampleUI implements InventoryUI {

	@Override
	public Function<Player, InventoryLayout> layout() {
		return build(InventoryLine.x1, (l) -> {

			l.onClose((event) -> {
				event.player.getWorld().playSound(event.player.getLocation(), Sound.AMBIENT_CAVE, 1f, 1f);
			});

			l.put((s) -> {
				s.icon(Material.NETHER_STAR, (i) -> {
					i.displayName = StringTemplate.apply("Your Level: $0", l.player.getLevel());

					if(i.isGleaming())
						i.tarnish();
					else
						i.gleam();
				});

				s.onClick((event) -> {
					event.player.sendMessage("SampleUI");
				});

			}, 0, 1, 2);

		});
	}

}

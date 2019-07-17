package amata1219.amalib.inventory.ui;

import java.util.function.Function;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import amata1219.amalib.StringTemplate;
import amata1219.amalib.inventory.ui.dsl.InventoryUI;
import amata1219.amalib.inventory.ui.dsl.component.InventoryLayout;
import amata1219.amalib.inventory.ui.option.InventoryLine;

public class SampleUI implements InventoryUI {

	@Override
	public Function<Player, InventoryLayout> layout() {
		//9スロットのインベントリを作成する
		return build(InventoryLine.x1, (l) -> {

			//インベントリを閉じた時のアクションを設定する
			l.onClose((event) -> {
				event.player.getWorld().playSound(event.player.getLocation(), Sound.AMBIENT_CAVE, 1f, 1f);
			});

			//0, 1, 2 番目のスロットにセットする
			l.put((s) -> {
				//アイコンの見た目をネザースターにする
				s.icon(Material.NETHER_STAR, (i) -> {
					//表示名を設定する
					i.displayName = StringTemplate.format("Your Level: $0", l.player.getLevel());

					//エンチャントオーラを付与する
					i.gleam();
				});

				//クリック時のアクションを設定する
				s.onClick((event) -> {
					event.player.sendMessage("メッセージを送ってみる");
				});

			}, 0, 1, 2);

		});
	}

}

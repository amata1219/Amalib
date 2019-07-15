package amata1219.amalib.inventory.ui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class InventoryUI implements InventoryHolder {

	protected final InventoryLayout layout;

	public InventoryUI(Applier<InventoryLayout> build){
	}

	public void openInventory(Player player){
		player.openInventory(layout.buidInventory());
	}



	@Override
	public Inventory getInventory(){
		throw new UnsupportedOperationException();
	}

}

package amata1219.amalib.inventory.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;


public class Icon {

	private final ItemStack base;
	private final Material material;
	private final int amount;
	private final short durability;
	private final String name;
	private final ArrayList<String> lore = new ArrayList<>();
	private final HashMap<Enchantment, Integer> enchantments = new HashMap<>();
	private final HashSet<ItemFlag> flags = new HashSet<>();

	public ItemStack toItemStack(){

	}

	public void glow(){
		enchantments.put(new GlowEnchant(), 1);
	}

}

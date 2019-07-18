package amata1219.amalib.inventory.ui.dsl.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import amata1219.amalib.inventory.ui.Applier;

public class Icon {

	public ItemStack basedItemStack;
	public Material material = Material.AIR;
	public int amount = 1;
	public int damage;
	public String displayName;
	public List<String> lore = new ArrayList<>();
	public Map<Enchantment, Integer> enchantments = new HashMap<>();
	public Set<ItemFlag> flags = new HashSet<>();
	private Applier<ItemStack> raw;

	static{
		Enchantment.registerEnchantment(new GleamEnchantment());
	}

	public ItemStack toItemStack(){
		ItemStack item = basedItemStack != null ? basedItemStack.clone() : new ItemStack(material);

		item.setAmount(amount);

		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(displayName);
		meta.setLore(lore);

		for(Entry<Enchantment, Integer> entry : enchantments.entrySet())
			meta.addEnchant(entry.getKey(), entry.getValue(), true);

		meta.addItemFlags(flags.toArray(new ItemFlag[flags.size()]));

		if(meta instanceof Damageable)
			((Damageable) meta).setDamage(damage);

		return raw.apply(item);
	}

	public void lore(String... texts){
		lore.addAll(Arrays.asList(texts));
	}

	public void raw(Applier<ItemStack> applier){
		raw = applier;
	}

	public void gleam(){
		enchantments.put(new GleamEnchantment(), 1);
	}

	public void tarnish(){
		enchantments.remove(new GleamEnchantment());
	}

	private static class GleamEnchantment extends Enchantment {

		private GleamEnchantment() {
			super(NamespacedKey.minecraft("gleam"));
		}

		@Override
		public boolean canEnchantItem(ItemStack arg0) {
			return false;
		}

		@Override
		public boolean conflictsWith(Enchantment arg0) {
			return false;
		}

		@Override
		public EnchantmentTarget getItemTarget() {
			return EnchantmentTarget.ALL;
		}

		@Override
		public int getMaxLevel() {
			return 0;
		}

		@Override
		public String getName() {
			return "gleam";
		}

		@Override
		public int getStartLevel() {
			return 0;
		}

		@Override
		public boolean isCursed() {
			return false;
		}

		@Override
		public boolean isTreasure() {
			return false;
		}

	}

}

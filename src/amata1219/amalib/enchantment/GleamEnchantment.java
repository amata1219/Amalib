package amata1219.amalib.enchantment;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class GleamEnchantment extends Enchantment {

	public static final GleamEnchantment GLEAM_ENCHANTMENT = new GleamEnchantment();

	public static void gleam(ItemStack item){
		item.addEnchantment(GLEAM_ENCHANTMENT, 0);
	}

	public static void tarnish(ItemStack item){
		item.removeEnchantment(GLEAM_ENCHANTMENT);
	}

	public static boolean isGleaming(ItemStack item){
		return item.getItemMeta().hasEnchant(GLEAM_ENCHANTMENT);
		//return item.containsEnchantment(GLEAM_ENCHANTMENT);
	}

	private GleamEnchantment() {
		super(NamespacedKey.minecraft("gleam"));
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		return true;
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

package amata1219.amalib.inventory.ui;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class GleamEnchantment extends Enchantment {

	static{
		Enchantment.registerEnchantment(new GleamEnchantment());
	}

	public GleamEnchantment() {
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

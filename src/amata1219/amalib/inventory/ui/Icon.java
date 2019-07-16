package amata1219.amalib.inventory.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class Icon {

	public ItemStack basedItem;
	public Material material;
	public int amount = 1;
	public int damage;
	public String displayName;
	public List<String> lore = new ArrayList<>();
	public Map<Enchantment, Integer> enchantments = new HashMap<>();
	public Set<ItemFlag> flags = new HashSet<>();
	private Applicator<ItemStack> rawApplicator;

	public ItemStack toItemStack(){
		ItemStack item = basedItem != null ? basedItem.clone() : new ItemStack(material);

		item.setAmount(amount);

		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(displayName);
		meta.setLore(lore);

		for(Entry<Enchantment, Integer> entry : enchantments.entrySet())
			meta.addEnchant(entry.getKey(), entry.getValue(), true);

		meta.addItemFlags(flags.toArray(new ItemFlag[flags.size()]));

		if(meta instanceof Damageable)
			((Damageable) meta).setDamage(damage);

		return rawApplicator.applicate(item);
	}

	public void lore(String... texts){
		lore.addAll(Arrays.asList(texts));
	}

	public void raw(Applicator<ItemStack> applicator){
		rawApplicator = applicator;
	}

	public void gleam(){
		enchantments.put(new GleamEnchantment(), 1);
	}

}

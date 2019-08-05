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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import amata1219.amalib.enchantment.GleamEnchantment;
import amata1219.amalib.inventory.ui.Applier;

public class Icon {

	//基となるアイテム
	public ItemStack basedItemStack;

	//マテリアル
	public Material material = Material.AIR;

	//個数
	public int amount = 1;

	//耐久値
	public int damage;

	//表示名
	public String displayName;

	//説明文
	public List<String> lore = new ArrayList<>();

	//エンチャントのマップ
	public Map<Enchantment, Integer> enchantments = new HashMap<>();

	//アイテムフラグのリスト
	public Set<ItemFlag> flags = new HashSet<>();

	//ItemStack自体に適用する処理
	public Applier<ItemStack> raw;

	public Icon(){

	}

	public ItemStack toItemStack(){
		ItemStack item = basedItemStack != null ? basedItemStack.clone() : new ItemStack(material);
		apply(item);
		return item;
	}

	//受け取ったアイテムをアイコンの情報で上書きする
	public void apply(ItemStack item){
		item.setAmount(amount);

		if(item.hasItemMeta()){
			ItemMeta meta = item.getItemMeta();

			meta.setDisplayName(displayName);
			meta.setLore(lore);

			for(Entry<Enchantment, Integer> entry : enchantments.entrySet())
				meta.addEnchant(entry.getKey(), entry.getValue(), true);

			meta.addItemFlags(flags.toArray(new ItemFlag[flags.size()]));

			if(meta instanceof Damageable)
				((Damageable) meta).setDamage(damage);

			item.setItemMeta(meta);
		}

		if(raw != null)
			raw.apply(item);
	}

	//アイコンの情報を受け取ったアイテムのそれで上書きする
	public void overwrite(ItemStack item){
		basedItemStack = item;

		material = item.getType();
		amount = item.getAmount();

		if(item.hasItemMeta()){
			ItemMeta meta = item.getItemMeta();

			damage = meta instanceof Damageable ? ((Damageable) meta).getDamage() : 0;

			displayName = meta.getDisplayName();
			lore.addAll(meta.getLore());

			enchantments.putAll(meta.getEnchants());

			flags.addAll(meta.getItemFlags());
		}
	}

	public void lore(String... texts){
		lore = Arrays.asList(texts);
	}

	public void gleam(){
		enchantments.put(GleamEnchantment.GLEAM_ENCHANTMENT, 1);
	}

	public boolean isGleaming(){
		return enchantments.containsKey(GleamEnchantment.GLEAM_ENCHANTMENT);
	}

	public void tarnish(){
		enchantments.remove(GleamEnchantment.GLEAM_ENCHANTMENT);
	}

}

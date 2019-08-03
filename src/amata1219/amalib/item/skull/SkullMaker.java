package amata1219.amalib.item.skull;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import amata1219.amalib.string.StringTemplate;

public class SkullMaker {

	private static final ItemStack BASE_SKULL = new ItemStack(Material.PLAYER_HEAD);

	public static ItemStack fromPlayerUniqueId(UUID uuid){
		return fromOfflinePlayer(Bukkit.getOfflinePlayer(uuid));
	}

	public static ItemStack fromOfflinePlayer(OfflinePlayer player){
		ItemStack skull = BASE_SKULL.clone();

		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwningPlayer(player);

		skull.setItemMeta(meta);

		return skull;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack fromBase64(String base64){
		int hash = base64.hashCode();
		UUID uuid = new UUID(hash, hash);
		return Bukkit.getUnsafe().modifyItemStack(BASE_SKULL.clone(), StringTemplate.apply("{SkullOwner:{Id:\"$0\",Properties:{textures:[{Value:\"$1\"}]}}}", uuid, base64));
	}

}

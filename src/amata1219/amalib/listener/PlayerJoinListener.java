package amata1219.amalib.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public interface PlayerJoinListener extends Listener {

	@EventHandler
	void onJoin(PlayerJoinEvent event);

}

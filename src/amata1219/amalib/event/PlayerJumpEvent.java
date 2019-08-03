package amata1219.amalib.event;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJumpEvent extends PlayerEvent {

	public static final PlayerJumpEventListener listener = new PlayerJumpEventListener();
	private static final HandlerList handlers = new HandlerList();

	public PlayerJumpEvent(Player player){
		super(player);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList(){
		return handlers;
	}

	private static class PlayerJumpEventListener implements Listener {

		private HashMap<UUID, Integer> jumps = new HashMap<>();

		private PlayerJumpEventListener(){

		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPlayerJoin(PlayerJoinEvent event) {
			Player player = event.getPlayer();
			jumps.put(player.getUniqueId(), getJumps(player));
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPlayerQuit(PlayerQuitEvent event) {
			jumps.remove(event.getPlayer().getUniqueId());
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPlayerMove(PlayerMoveEvent event) {
			Player player = event.getPlayer();

			double fromY = event.getFrom().getY();
			double toY = event.getTo().getY();

			if(fromY >= toY)
				return;

			int current = getJumps(player);
			int last = jumps.getOrDefault(player.getUniqueId(), -1);

			if(current == last)
				return;

			jumps.put(player.getUniqueId(), current);

			double diff = (long) ((event.getTo().getY() - event.getFrom().getY()) * 1000) / 1000D;

			if((diff < 0.035 || diff > 0.037) && (diff < 0.116 || diff > 0.118))
				Bukkit.getPluginManager().callEvent(new PlayerJumpEvent(player));
		}

		private int getJumps(Player player){
			return player.getStatistic(Statistic.JUMP);
		}

	}

}

package amata1219.amalib.schedule;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import amata1219.amalib.Amalib;

public interface Async extends Runnable {

	public static Async write(Async async){
		return async;
	}

	public default BukkitTask execute(){
		return Bukkit.getScheduler().runTaskAsynchronously(Amalib.getPlugin(), this);
	}

	public default BukkitTask executeLater(long delay){
		return Bukkit.getScheduler().runTaskLaterAsynchronously(Amalib.getPlugin(), this, delay);
	}

	public default BukkitTask executeTimer(long period, long delay){
		return Bukkit.getScheduler().runTaskTimerAsynchronously(Amalib.getPlugin(), this, period, delay);
	}

}

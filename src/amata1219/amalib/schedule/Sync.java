package amata1219.amalib.schedule;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import amata1219.amalib.Amalib;

public interface Sync extends Runnable {

	public static Sync define(Sync sync){
		return sync;
	}

	public default void execute(){
		Bukkit.getScheduler().runTask(Amalib.getPlugin(), this);
	}

	public default void executeLater(long delay){
		Bukkit.getScheduler().runTaskLater(Amalib.getPlugin(), this, delay);
	}

	public default BukkitTask executeTimer(long period, long delay){
		return Bukkit.getScheduler().runTaskTimer(Amalib.getPlugin(), this, period, delay);
	}

}

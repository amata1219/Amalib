package amata1219.amalib.command;

import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.ProxiedCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

public class Sender {

	public final CommandSender sender;

	public Sender(CommandSender sender){
		this.sender = sender;
	}

	public boolean isConsoleCommandSender(){
		return SenderType.CONSOLE.isSender(this);
	}

	public boolean isPlayerCommandSender(){
		return SenderType.PLAYER.isSender(this);
	}

	public boolean isBlockCommandSender(){
		return SenderType.BLOCK.isSender(this);
	}

	public boolean isRemoteConsoleCommandSender(){
		return SenderType.REMOTE_CONSOLE.isSender(this);
	}

	public boolean isProxiedCommandSender(){
		return SenderType.PROXIED.isSender(this);
	}

	public void info(String message){
		sender.sendMessage(ChatColor.AQUA + message);
	}

	public void warn(String message){
		sender.sendMessage(ChatColor.RED + message);
	}

	public void tip(String message){
		sender.sendMessage(ChatColor.GRAY + message);
	}

	private enum SenderType {

		CONSOLE(ConsoleCommandSender.class),
		PLAYER(Player.class),
		BLOCK(BlockCommandSender.class),
		REMOTE_CONSOLE(RemoteConsoleCommandSender.class),
		PROXIED(ProxiedCommandSender.class);

		public final Class<?> clazz;

		private SenderType(Class<?> clazz){
			this.clazz = clazz;
		}

		public boolean isSender(Sender sender){
			return clazz.isInstance(sender.sender);
		}

	}

}

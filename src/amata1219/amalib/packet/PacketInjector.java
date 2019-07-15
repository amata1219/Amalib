package amata1219.amalib.packet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import amata1219.amalib.Reflection;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;

public class PacketInjector {

	public static final String InjectorName = "amalib_injector";
	public static final Method getHandle;
	public static final Field playerConnection;
	public static final Field networkManager;

	public static final Field channel;

	static{
		Class<?> CraftPlayer = Reflection.getOBCClass("entity.CraftPlayer");
		getHandle = Reflection.getMethod(CraftPlayer, "getHandle");

		Class<?> EntityPlayer = Reflection.getNMSClass("EntityPlayer");
		playerConnection = Reflection.getField(EntityPlayer, "playerConnection");

		Class<?> PlayerConnection = Reflection.getNMSClass("PlayerConnection");
		networkManager = Reflection.getField(PlayerConnection, "networkManager");

		Class<?> NetworkManager = Reflection.getNMSClass("NetworkManager");
		channel = Reflection.getField(NetworkManager, "channel");
	}

	private PacketInjector(){

	}

	public static void addPlayer(Player player) {
		Channel channel = getChannel(player);
		ChannelPipeline pipeline = channel.pipeline();
		if(pipeline.get(InjectorName) != null)
			return;

		PacketHandler packetHandler = new PacketHandler(player);
		pipeline.addBefore("packet_handler", InjectorName, packetHandler);
	}

	public static void removePlayer(Player player) {
		Channel channel = getChannel(player);
		ChannelPipeline pipeline = channel.pipeline();
		if(pipeline.get(InjectorName) != null)
			pipeline.remove(InjectorName);
	}

	private static Channel getChannel(Player player) {
		Object entityPlayer = Reflection.invokeMethod(getHandle, player);
		Object playerConnection = Reflection.getFieldValue(PacketInjector.playerConnection, entityPlayer);
		Object networkManager = Reflection.getFieldValue(PacketInjector.networkManager, playerConnection);
		return Reflection.getFieldValue(PacketInjector.channel, networkManager);
	}

}

package amata1219.amalib.packet;

import static amata1219.amalib.reflection.Reflection.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;

public class PacketInjector {

	public static final String InjectorName = "amalib_injector";
	public static final Method getHandle;
	public static final Field playerConnection;
	public static final Field networkManager;

	public static final Field channel;

	static{
		Class<?> CraftPlayer = getOBCClass("entity.CraftPlayer");
		getHandle = getMethod(CraftPlayer, "getHandle");

		Class<?> EntityPlayer = getNMSClass("EntityPlayer");
		playerConnection = getField(EntityPlayer, "playerConnection");

		Class<?> PlayerConnection = getNMSClass("PlayerConnection");
		networkManager = getField(PlayerConnection, "networkManager");

		Class<?> NetworkManager = getNMSClass("NetworkManager");
		channel = getField(NetworkManager, "channel");
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
		Object entityPlayer = invokeMethod(getHandle, player);

		Object playerConnection = getFieldValue(PacketInjector.playerConnection, entityPlayer);

		Object networkManager = getFieldValue(PacketInjector.networkManager, playerConnection);

		return getFieldValue(PacketInjector.channel, networkManager);
	}

}

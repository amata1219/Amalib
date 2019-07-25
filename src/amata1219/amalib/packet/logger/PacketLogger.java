package amata1219.amalib.packet.logger;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import amata1219.amalib.packet.PacketHandler;
import amata1219.amalib.reflection.Reflection;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_13_R2.Advancement;
import net.minecraft.server.v1_13_R2.AdvancementDisplay;
import net.minecraft.server.v1_13_R2.AdvancementRequirements;
import net.minecraft.server.v1_13_R2.AdvancementRewards;
import net.minecraft.server.v1_13_R2.Criterion;
import net.minecraft.server.v1_13_R2.MinecraftKey;
import net.minecraft.server.v1_13_R2.PacketPlayOutAdvancements;
import net.minecraft.server.v1_13_R2.Advancement.SerializedAdvancement;

public class PacketLogger extends PacketHandler {

	/*public static void logging(Player player) {
		Channel channel = getChannel(player);
		ChannelPipeline pipeline = channel.pipeline();

		if(pipeline.get(PacketInjector.InjectorName) != null)
			return;

		PacketLogger packetHandler = new PacketLogger(player);
		pipeline.addBefore("packet_handler", PacketInjector.InjectorName, packetHandler);
	}

	private static Channel getChannel(Player player) {
		Object entityPlayer = invokeMethod(PacketInjector.getHandle, player);

		Object playerConnection = getFieldValue(PacketInjector.playerConnection, entityPlayer);

		Object networkManager = getFieldValue(PacketInjector.networkManager, playerConnection);

		return getFieldValue(PacketInjector.channel, networkManager);
	}*/

	public PacketLogger(Player player){
		super(player);
	}

	@Override
	public void write(ChannelHandlerContext context, Object message, ChannelPromise promise) throws Exception {
		String name = message.getClass().getSimpleName();
		if(name.equals("PacketPlayOutAdvancements")){
			System.out.println("write @ " + name);
			PacketPlayOutAdvancements packet = (PacketPlayOutAdvancements) message;
			Map<MinecraftKey, SerializedAdvancement> b = Reflection.getFieldValue(Reflection.getField(PacketPlayOutAdvancements.class, "b"), packet);
			for(Entry<MinecraftKey, SerializedAdvancement> entry : b.entrySet()){
				MinecraftKey key = entry.getKey();
				System.out.println(key.toString());
				SerializedAdvancement ad = entry.getValue();
				/*
				 * private MinecraftKey a;
		private Advancement b;
		private AdvancementDisplay c;
		private AdvancementRewards d;
		private Map<String, Criterion> e;
		private String[][] f;
		private AdvancementRequirements g;
				 */
				Class<SerializedAdvancement> cl = SerializedAdvancement.class;
				MinecraftKey akey = Reflection.getFieldValue(Reflection.getField(cl, "a"), ad);
				Advancement bb = Reflection.getFieldValue(Reflection.getField(cl, "b"), ad);
				AdvancementDisplay c = Reflection.getFieldValue(Reflection.getField(cl, "c"), ad);
				AdvancementRewards d = Reflection.getFieldValue(Reflection.getField(cl, "d"), ad);
				Map<String, Criterion> e = Reflection.getFieldValue(Reflection.getField(cl, "e"), ad);
				String[][] f = Reflection.getFieldValue(Reflection.getField(cl, "f"), ad);
				AdvancementRequirements g = Reflection.getFieldValue(Reflection.getField(cl, "g"), ad);
				System.out.println("MinecraftKey: " + (akey != null ? akey.toString() : "null"));
				System.out.println("Advancement: " + (bb != null ? bb.toString() : "null"));
				System.out.println("AdvancementDisplay: " + (c != null ? c.toString() : "null"));
				System.out.println("AdvancementRewards: " + (d != null ? d.toString() : "null"));
				System.out.println("Map<String, Criterion>t: " + (e != null ? e.toString() : "null"));
				System.out.println("String[][]: " + (f != null ? f.toString() : "null"));
				System.out.println("AdvancementRequirements: " + (g != null ? g.toString() : "null"));
				System.out.println("------------------------------------");
			}
		}
		super.write(context, message, promise);
	}

	@Override
	public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
		//System.out.println("read @ " + message.getClass().getSimpleName());
		super.channelRead(context, message);
	}

}

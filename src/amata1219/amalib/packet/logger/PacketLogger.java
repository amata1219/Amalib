package amata1219.amalib.packet.logger;

import org.bukkit.entity.Player;

import amata1219.amalib.packet.PacketHandler;
import amata1219.amalib.packet.PacketInjector;
import amata1219.amalib.reflection.Reflection;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.IScoreboardCriteria.EnumScoreboardHealthDisplay;
import net.minecraft.server.v1_13_R2.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_13_R2.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_13_R2.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_13_R2.ScoreboardServer.Action;

public class PacketLogger extends PacketHandler {

	public static void logging(Player player) {
		Channel channel = getChannel(player);
		ChannelPipeline pipeline = channel.pipeline();

		if(pipeline.get(PacketInjector.InjectorName) != null)
			return;

		PacketLogger packetHandler = new PacketLogger(player);
		pipeline.addBefore("packet_handler", PacketInjector.InjectorName, packetHandler);
	}

	private static Channel getChannel(Player player) {
		Object entityPlayer = Reflection.invokeMethod(PacketInjector.getHandle, player);

		Object playerConnection = Reflection.getFieldValue(PacketInjector.playerConnection, entityPlayer);

		Object networkManager = Reflection.getFieldValue(PacketInjector.networkManager, playerConnection);

		return Reflection.getFieldValue(PacketInjector.channel, networkManager);
	}

	public PacketLogger(Player player){
		super(player);
	}

	@Override
	public void write(ChannelHandlerContext context, Object message, ChannelPromise promise) throws Exception {
		String name = message.getClass().getSimpleName();
		if(name.equals("PacketPlayOutScoreboardDisplayObjective")){
			PacketPlayOutScoreboardDisplayObjective packet = (PacketPlayOutScoreboardDisplayObjective) message;
			int a = Reflection.getFieldValue(Reflection.getField(packet.getClass(), "a"), packet);
			String b = Reflection.getFieldValue(Reflection.getField(packet.getClass(), "b"), packet);
			log("PacketPlayOutScoreboardDisplayObjective");
			log("int a: " + a);
			log("String b: " + b);
		}else if(name.equals("PacketPlayOutScoreboardObjective")){
			PacketPlayOutScoreboardObjective packet = (PacketPlayOutScoreboardObjective) message;
			String a = Reflection.getFieldValue(Reflection.getField(packet.getClass(), "a"), packet);
			IChatBaseComponent b = Reflection.getFieldValue(Reflection.getField(packet.getClass(), "b"), packet);
			EnumScoreboardHealthDisplay c = Reflection.getFieldValue(Reflection.getField(packet.getClass(), "c"), packet);
			int d = Reflection.getFieldValue(Reflection.getField(packet.getClass(), "d"), packet);
			log("PacketPlayOutScoreboardObjective");
			log("String a: " + a);
			log("IChatBaseComponent b: " + b.getText());
			log("EnumScoreboardHealthDisplay c: " + c.toString());
			log("int d: " + d);
		}else if(name.equals("PacketPlayOutScoreboardScore")){
			PacketPlayOutScoreboardScore packet = (PacketPlayOutScoreboardScore) message;
			String a = Reflection.getFieldValue(Reflection.getField(packet.getClass(), "a"), packet);
			String b = Reflection.getFieldValue(Reflection.getField(packet.getClass(), "b"), packet);
			int c = Reflection.getFieldValue(Reflection.getField(packet.getClass(), "c"), packet);
			Action d = Reflection.getFieldValue(Reflection.getField(packet.getClass(), "d"), packet);
			log("PacketPlayOutScoreboardScore");
			log("String a: " + a);
			log("String b: " + b);
			log("int c: " + c);
			log("Action d: " + d.toString());
		}
		/*if(name.equals("PacketPlayOutAdvancements")){
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
		private AdvancementRequirements g;/

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
		}*/
		super.write(context, message, promise);
	}

	@Override
	public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
		//System.out.println("read @ " + message.getClass().getSimpleName());
		super.channelRead(context, message);
	}

	private void log(String s){
		System.out.println(s);
	}

}

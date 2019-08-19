package amata1219.amalib.packet.logger;

import org.bukkit.entity.Player;

import amata1219.amalib.packet.PacketHandler;
import amata1219.amalib.packet.PacketInjector;
import amata1219.amalib.reflection.Reflection;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;

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
		//String name = message.getClass().getSimpleName();
		//System.out.println(name);

		/*
		 * PacketPlayOutPlayerInfo
		 * PacketPlayOutCustomPayload
		 */

		/*if(name.equals("PacketPlayOutScoreboardDisplayObjective")){
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
		}*/
		/*if(name.equals("PacketPlayOutAdvancements")){
		/*	System.out.println("write @ " + name);
			PacketPlayOutAdvancements packet = (PacketPlayOutAdvancements) message;
			Map<MinecraftKey, SerializedAdvancement> b = Reflection.getFieldValue(Reflection.getField(PacketPlayOutAdvancements.class, "b"), packet);
			for(Entry<MinecraftKey, SerializedAdvancement> entry : b.entrySet()){
				MinecraftKey key = entry.getKey();
				System.out.println(key.toString());
				SerializedAdvancement ad = entry.getValue();*/
				 /*private MinecraftKey a;
		private Advancement b;
		private AdvancementDisplay c;
		private AdvancementRewards d;
		private Map<String, Criterion> e;
		private String[][] f;
		private AdvancementRequirements g;*/

		/*		Class<SerializedAdvancement> cl = SerializedAdvancement.class;
				MinecraftKey akey = Reflection.getFieldValue(Reflection.getField(cl, "a"), ad);
				Advancement bb = Reflection.getFieldValue(Reflection.getField(cl, "b"), ad);




				AdvancementDisplay c = Reflection.getFieldValue(Reflection.getField(cl, "c"), ad);*/

				/*
				 * ichatbasecomponent a = icon
				 * ichatbasecomponent b = title
				 * itemstack c = description:empty
				 * minecraftkey d = background:task
				 *advancementframetype e = frame:task
				 *bool f = show_toast:true
				 *bool g = announce_to_chat:false
				 *bool h = hidden:false
				 *
				 * new AD(icon, title, description:empty, background:bedrock, frame:task, show_toast:true, announce_to_chat:false, hidden:false)
*/

			/*	AdvancementRewards d = Reflection.getFieldValue(Reflection.getField(cl, "d"), ad);
				Map<String, Criterion> e = Reflection.getFieldValue(Reflection.getField(cl, "e"), ad);
				String[][] f = Reflection.getFieldValue(Reflection.getField(cl, "f"), ad);
				AdvancementRequirements g = Reflection.getFieldValue(Reflection.getField(cl, "g"), ad);
				System.out.println("MinecraftKey: " + (akey != null ? akey.toString() : "null"));
				System.out.println("Advancement: " + (bb != null ? bb.toString() : "null"));
				System.out.println("AdvancementDisplay: " + (c != null ? c.toString() : "null"));
				System.out.println("AdvancementRewards: " + (d != null ? d.toString() : "null"));
				//System.out.println("Map<String, Criterion>t: " + (e != null ? e.toString() : "null"));
				if(e != null){
					e.entrySet().forEach(eeee -> log(eeee.getKey().toString() + " : " + eeee.getValue().toString()));
				}
				//System.out.println("String[][]: " + (f != null ? f.toString() : "null"));
				if(f != null){
					for(int index = 0; index < f.length; index++){
						String[] aaa = f[index];
						if(aaa != null){
							for(int inde = 0; inde < aaa.length; inde++)
								log(index + " : " + inde + " : " + aaa[inde]);
						}
					}
				}

				System.out.println("AdvancementRequirements: " + (g != null ? g.toString() : "null"));
				System.out.println("------------------------------------");
			}
		}*/
		super.write(context, message, promise);
	}

	@Override
	public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
		/*String name = message.getClass().getSimpleName();
		System.out.println("read @ " + name);
		if(name.equals("PacketPlayInSettings")){
			PacketPlayInSettings packet = (PacketPlayInSettings) message;
			String language = Reflection.getFieldValue(Reflection.getField(packet.getClass(), "a"), packet);
			System.out.println(language);
		}
		/*
		 * PacketPlayInSettings
		 */
		super.channelRead(context, message);
	}

	public void log(String s){
		System.out.println(s);
	}

}

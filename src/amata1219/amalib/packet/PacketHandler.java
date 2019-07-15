package amata1219.amalib.packet;

import org.bukkit.entity.Player;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class PacketHandler extends ChannelDuplexHandler {

	public final Player player;

	public PacketHandler(Player player) {
		this.player = player;
	}

	@Override
	public void write(ChannelHandlerContext context, Object message, ChannelPromise promise) throws Exception {
		if(player.isSwimming() && message.getClass().getSimpleName().equalsIgnoreCase("PacketPlayOutRelEntityMove"))
			return;

		super.write(context, message, promise);
	}

	@Override
	public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
		if(player.isSwimming() && message.getClass().getSimpleName().equalsIgnoreCase("PacketPlayOutRelEntityMove"))
			return;

		super.channelRead(context, message);
	}

}

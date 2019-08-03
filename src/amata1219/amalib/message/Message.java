package amata1219.amalib.message;

import java.util.Collection;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;

public class Message {

	public final String text;

	public static Message wrap(String text){
		return new Message(text);
	}

	public Message(String text){
		this.text = text;
	}

	public void display(Player player){
		player.sendMessage(text);
	}

	public void display(Collection<Player> players){
		for(Player player : players) display(player);
	}

	public void displayOnActionBar(Player player){
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
	}

	public void displayOnActionBar(Collection<Player> players){
		TextComponent component = new TextComponent(text);

		for(Player player : players) player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
	}

	public void sendAsClickable(Player player, ClickAction clickAction, String value){
		TextComponent component = new TextComponent(text);
		component.setClickEvent(new ClickEvent(clickAction.action, value));
		sendComponent(player, component);
	}

	public void sendAsClickable(Collection<Player> players, ClickAction clickAction, String value){
		TextComponent component = new TextComponent(text);
		component.setClickEvent(new ClickEvent(clickAction.action, value));
		sendComponentToAll(players, component);
	}

	public void sendAsHoverable(Player player, HoverAction hoverAction, String... hoverTexts){
		TextComponent component = new TextComponent(text);
		component.setHoverEvent(new HoverEvent(hoverAction.action, toHoverComponents(hoverTexts)));
		sendComponent(player, component);
	}

	public void sendAsHoverable(Collection<Player> players, HoverAction hoverAction, String... hoverTexts){
		TextComponent component = new TextComponent(text);
		component.setHoverEvent(new HoverEvent(hoverAction.action, toHoverComponents(hoverTexts)));
		sendComponentToAll(players, component);
	}

	public void displayAsClickableAndHoverable(Player player, ClickAction clickAction, String value, HoverAction hoverAction, String... hoverTexts){
		TextComponent component = new TextComponent(text);
		component.setClickEvent(new ClickEvent(clickAction.action, value));
		component.setHoverEvent(new HoverEvent(hoverAction.action, toHoverComponents(hoverTexts)));
		sendComponent(player, component);
	}

	public void displayAsClickableAndHoverable(Collection<Player> players, ClickAction clickAction, String value, HoverAction hoverAction, String... hoverTexts){
		TextComponent component = new TextComponent(text);
		component.setClickEvent(new ClickEvent(clickAction.action, value));
		component.setHoverEvent(new HoverEvent(hoverAction.action, toHoverComponents(hoverTexts)));
		sendComponentToAll(players, component);
	}

	private TextComponent[] toHoverComponents(String... texts){
		TextComponent[] components = new TextComponent[texts.length];
		for(int index = 0; index < texts.length; index++)
			components[index] = new TextComponent(texts[index]);

		return components;
	}

	private void sendComponent(Player player, TextComponent component){
		player.spigot().sendMessage(component);
	}

	private void sendComponentToAll(Collection<Player> players, TextComponent component){
		for(Player player : players) sendComponent(player, component);
	}

	public static enum HoverAction {

		SHOW_TEXT(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT),
		SHOW_ACHIEVEMENT(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_ACHIEVEMENT),
		SHOW_ITEM(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_ITEM),
		SHOW_ENTITY(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_ENTITY);

		public final net.md_5.bungee.api.chat.HoverEvent.Action action;

		private HoverAction(net.md_5.bungee.api.chat.HoverEvent.Action action){
			this.action = action;
		}

	}

	public static enum ClickAction {

		OPEN_URL(net.md_5.bungee.api.chat.ClickEvent.Action.OPEN_URL),
		OPEN_FILE(net.md_5.bungee.api.chat.ClickEvent.Action.OPEN_FILE),
		RUN_COMMAND(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND),
		SUGGEST_COMMAND(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND),
		CHANGE_PAGE(net.md_5.bungee.api.chat.ClickEvent.Action.CHANGE_PAGE);

		public final net.md_5.bungee.api.chat.ClickEvent.Action action;

		private ClickAction(net.md_5.bungee.api.chat.ClickEvent.Action action){
			this.action = action;
		}
	}

}

package amata1219.amalib.string.message;

import java.util.Collection;
import java.util.function.BiConsumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import amata1219.amalib.string.StringLocalize;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;

public class Message {

	private final String text;
	private boolean localize;

	public static Message wrap(String text){
		return new Message(text);
	}

	public Message(String text){
		this.text = text;
	}

	public Message localize(){
		localize = true;
		return this;
	}

	public void display(Player player){
		player.sendMessage(getCorrespondText(player, text));
	}

	public void display(Collection<? extends Player> players){
		for(Player player : players) display(player);
	}

	public void broadcast(){
		display(Bukkit.getOnlinePlayers());
	}

	public void displayOnActionBar(Player player){
		sendComponent(player, (receiver, component) -> {}, ChatMessageType.ACTION_BAR);
	}

	public void displayOnActionBar(Collection<Player> players){
		for(Player player : players) displayOnActionBar(player);
	}

	public void sendAsClickable(Player player, ClickAction clickAction, String clickText){
		sendComponent(player, (receiver, component) -> component.setClickEvent(new ClickEvent(clickAction.action, getCorrespondText(receiver, clickText))));
	}

	public void sendAsClickable(Collection<Player> players, ClickAction clickAction, String clickText){
		for(Player player : players) sendAsClickable(player, clickAction, clickText);
	}

	public void sendAsHoverable(Player player, HoverAction hoverAction, String... hoverTexts){
		sendComponent(player, (receiver, component) -> component.setHoverEvent(new HoverEvent(hoverAction.action, toHoverComponents(receiver, hoverTexts))));
	}

	public void sendAsHoverable(Collection<Player> players, HoverAction hoverAction, String... hoverTexts){
		for(Player player : players) sendAsHoverable(player, hoverAction, hoverTexts);
	}

	public void displayAsClickableAndHoverable(Player player, ClickAction clickAction, String clickText, HoverAction hoverAction, String... hoverTexts){
		sendComponent(player, (receiver, component) -> {
			component.setClickEvent(new ClickEvent(clickAction.action, getCorrespondText(receiver, clickText)));
			component.setHoverEvent(new HoverEvent(hoverAction.action, toHoverComponents(receiver, hoverTexts)));
		});
	}

	public void displayAsClickableAndHoverable(Collection<Player> players, ClickAction clickAction, String clickText, HoverAction hoverAction, String... hoverTexts){
		for(Player player : players) displayAsClickableAndHoverable(player, clickAction, clickText, hoverAction, hoverTexts);
	}

	private TextComponent[] toHoverComponents(Player player, String... texts){
		TextComponent[] components = new TextComponent[texts.length];
		for(int index = 0; index < texts.length; index++)
			components[index] = new TextComponent(getCorrespondText(player, texts[index]));

		return components;
	}

	private void sendComponent(Player player, BiConsumer<Player, TextComponent> effect){
		sendComponent(player, effect, ChatMessageType.CHAT);
	}

	private void sendComponent(Player player, BiConsumer<Player, TextComponent> effect, ChatMessageType type){
		TextComponent component = new TextComponent(getCorrespondText(player, text));
		effect.accept(player, component);
		player.spigot().sendMessage(type, component);
	}

	private String getCorrespondText(Player player, String text){
		return localize ? StringLocalize.apply(text, player) : text;
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

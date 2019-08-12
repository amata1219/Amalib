package amata1219.amalib.sound;

import java.util.Collection;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class SoundMetadata {

	public final SoundCategory category;
	public final Sound sound;
	public final float volume;
	public final float pitch;

	public SoundMetadata(SoundCategory category, Sound sound, float volume, float pitch){
		this.category = category;
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
	}

	public SoundMetadata(Sound sound, float volume, float pitch){
		this(null, sound, volume, pitch);
	}

	public void play(Player player){
		player.playSound(player.getLocation(), sound, category != null ? category : SoundCategory.MASTER, volume, pitch);
	}

	public void play(Collection<Player> players){
		players.forEach(this::play);
	}

}

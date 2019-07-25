package amata1219.amalib.yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Yaml extends YamlConfiguration {

	public final JavaPlugin plugin;
	public final File file;
	public final String name;
	public final String resourceFileName;

	public Yaml(JavaPlugin plugin, String fileName){
		this(plugin, new File(plugin.getDataFolder(), fileName));
	}

	public Yaml(JavaPlugin plugin, File file){
		this(plugin, file, file.getName());
	}

	public Yaml(JavaPlugin plugin, File file, String resourceFileName){
		this.plugin = plugin;
		this.file = file;
		this.resourceFileName = resourceFileName;

		String fileName = file.getName();
		name = fileName.substring(0, fileName.length() - 4);

		if(!file.exists())
			plugin.saveResource(resourceFileName, false);
	}

	public void save(){
		try {
			save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reload(){
		super.map.clear();

		try {
			load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}

		InputStream input = plugin.getResource(resourceFileName);
		if(input != null)
			setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(input, StandardCharsets.UTF_8)));
	}

	public void update(){
		save();
		reload();
	}

}

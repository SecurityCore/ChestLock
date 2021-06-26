package de.fearmyshotz.chestlock.resources;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.fearmyshotz.chestlock.ChestLock;


public class ChestData {
	public static File file;
	public static FileConfiguration fileConfiguration;
	
	public static void setup() {
		file = new File(ChestLock.plugin.getDataFolder(), "chest-data.yml");
		
		if(!file.exists()) {
			ChestLock.plugin.saveResource("chest-data.yml", false);
		}
		
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
	}
	
	public static FileConfiguration get() {
		return YamlConfiguration.loadConfiguration(file);
	}
	
	public static void save() {
		try {
			fileConfiguration.save(file);
		} catch(IOException e) {
			ChestLock.plugin.getLogger().severe("Die Datei \"chest-data.yml\" konnte nicht gespeichert werden!");
		} 
	}
	
	public static void reload() {
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
	}
}

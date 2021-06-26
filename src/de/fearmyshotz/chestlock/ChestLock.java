package de.fearmyshotz.chestlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import de.fearmyshotz.chestlock.commands.AddAccessCommand;
import de.fearmyshotz.chestlock.commands.RemoveAccessCommand;
import de.fearmyshotz.chestlock.resources.ChestData;

public class ChestLock extends JavaPlugin {
	
	public static ChestLock plugin;
	public static String prefix;
	public static String coloredPrefix;
	
	@Override
	public void onEnable() {
		ChestLock.plugin = this;
		ChestLock.prefix = "§7[§6Chests§7] ";
		
		ChestData.setup();
		
		this.getCommand("addchest").setExecutor(new AddAccessCommand());
		this.getCommand("removechest").setExecutor(new RemoveAccessCommand());
	}

	@Override
	public void onDisable() {
		
	}
	
    public static void log(String message) {
        coloredPrefix = ChatColor.translateAlternateColorCodes('&', "&7[&6Chests&7] ");
        message = ChatColor.translateAlternateColorCodes('&', message);
        final String finalMessage = String.valueOf(coloredPrefix) + message;
        final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(finalMessage);
        return;
    }

}

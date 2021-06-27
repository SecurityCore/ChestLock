package de.fearmyshotz.chestlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.fearmyshotz.chestlock.commands.AddAccessCommand;
import de.fearmyshotz.chestlock.commands.ChestLockCommand;
import de.fearmyshotz.chestlock.commands.LockCommand;
import de.fearmyshotz.chestlock.commands.RemoveAccessCommand;
import de.fearmyshotz.chestlock.commands.UnlockCommand;
import de.fearmyshotz.chestlock.listeners.BlockBreakListener;
import de.fearmyshotz.chestlock.listeners.BlockPlaceListener;
import de.fearmyshotz.chestlock.listeners.InventoryMoveItemListener;
import de.fearmyshotz.chestlock.listeners.PlayerInteractListener;
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
		
		PluginManager pM = Bukkit.getServer().getPluginManager();
		pM.registerEvents(new BlockBreakListener(), this);
		pM.registerEvents(new BlockPlaceListener(), this);
		pM.registerEvents(new InventoryMoveItemListener(), this);
		pM.registerEvents(new PlayerInteractListener(), this);

		this.getCommand("lock").setExecutor(new LockCommand());
		this.getCommand("addchest").setExecutor(new AddAccessCommand());
		this.getCommand("unlock").setExecutor(new UnlockCommand());
		this.getCommand("removechest").setExecutor(new RemoveAccessCommand());
		this.getCommand("chest").setExecutor(new ChestLockCommand());
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

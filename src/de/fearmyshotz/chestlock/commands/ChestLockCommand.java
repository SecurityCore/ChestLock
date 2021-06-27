package de.fearmyshotz.chestlock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.fearmyshotz.chestlock.ChestLock;

public class ChestLockCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(command.getName().equalsIgnoreCase("chest")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(ChestLock.prefix + "§7ChestLock-Befehle: \n§7"
						+ "§7» /lock §e- Sperrt eine Kiste\n"
						+ "§7» /addchest Spieler §e- Gibt anderen Zugriff auf eine Kiste\n"
						+ "§7» /unlock §e- Gibt eine Kiste frei\n"
						+ "§7» /removechest Spieler§e- Entfernt anderen den Zugriff auf eine Kiste\n"
						+ "§7» /chest §e- Zeigt diese Hilfe an");
			} else {
				sender.sendMessage(ChestLock.prefix + "§cChestLock-Befehle können nur als Spieler ausgeführt werden!");
			}
		}
		return false;
	}

}

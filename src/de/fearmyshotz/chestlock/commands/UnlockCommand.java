package de.fearmyshotz.chestlock.commands;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.fearmyshotz.chestlock.ChestLock;
import de.fearmyshotz.chestlock.util.ChestLockUtil;

public class UnlockCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(command.getName().equalsIgnoreCase("unlock")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 0) {
					if(p.getTargetBlock((Set<Material>) null, 5) != null) {
						if(p.getTargetBlock((Set<Material>) null, 5).getType() == Material.CHEST) {
							if(ChestLockUtil.isChestRegistered(p.getTargetBlock((Set<Material>) null, 5))) {
								if(ChestLockUtil.isOwner(p, p.getTargetBlock((Set<Material>) null, 5))) {
									ChestLockUtil.removeChest(p.getTargetBlock((Set<Material>) null, 5));
									sender.sendMessage(ChestLock.prefix + "§aJeder Spieler hat nun Zugriff auf die Kiste!");
									return true;
								} else {
									sender.sendMessage(ChestLock.prefix + "§cNur der Besitzer der Kiste kann Spieler entfernen!");
									return false;
								}
							} else {
								sender.sendMessage(ChestLock.prefix + "§cDie Kiste ist nicht gesperrt!");
								return false;
							}
						} else {
							sender.sendMessage(ChestLock.prefix + "§cDu musst auf eine Kiste schauen, die von dir gesetzt wurde!");
							return false;
						}
					} else {
						sender.sendMessage(ChestLock.prefix + "§cDu musst auf eine Kiste schauen, die von dir gesetzt wurde!");
						return false;
					}
				} else {
					sender.sendMessage(ChestLock.prefix + "§cNutze §7/removechest");
					return false;
				}
			} else {
				sender.sendMessage(ChestLock.prefix + "§cChestLock-Befehle können nur als Spieler ausgeführt werden!");
				return false;
			}
		}
		return false;
	}
}

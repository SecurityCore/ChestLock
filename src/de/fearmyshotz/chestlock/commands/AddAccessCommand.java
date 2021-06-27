package de.fearmyshotz.chestlock.commands;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.fearmyshotz.chestlock.ChestLock;
import de.fearmyshotz.chestlock.util.ChestLockUtil;

public class AddAccessCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(command.getName().equalsIgnoreCase("addchest")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 1) {
					if(p.getTargetBlock((Set<Material>) null, 5) != null) {
						if(p.getTargetBlock((Set<Material>) null, 5).getType() == Material.CHEST) {
							if(ChestLockUtil.isChestRegistered(p.getTargetBlock((Set<Material>) null, 5))) {
								if(ChestLockUtil.isOwner(p, p.getTargetBlock((Set<Material>) null, 5))) {
									ChestLockUtil.addAccess(args[0], p);
									return true;
								} else {
									sender.sendMessage(ChestLock.prefix + "§cNur der Besitzer der Kiste kann Spieler hinzufügen!");
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
					sender.sendMessage(ChestLock.prefix + "§cNutze §7/addchest Spieler");
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

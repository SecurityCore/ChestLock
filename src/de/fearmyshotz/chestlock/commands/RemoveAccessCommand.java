package de.fearmyshotz.chestlock.commands;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.fearmyshotz.chestlock.ChestLock;
import de.fearmyshotz.chestlock.util.ChestLockUtil;

public class RemoveAccessCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(command.getName().equalsIgnoreCase("removechest")) {
			if(sender instanceof Player) {
				if(args.length == 1) {
					if(((Player) sender).getTargetBlock((Set<Material>) null, 5) != null) {
						if(ChestLockUtil.isOwner((Player) sender, ((Player) sender).getTargetBlock((Set<Material>) null, 5))) {
							ChestLockUtil.removeAccess(args[0], (Player) sender);
						} else {
							sender.sendMessage(ChestLock.prefix + "§cNur der Besitzer der Kiste kann Spieler entfernen!");
						}
					}
				}
			}
		}
		return false;
	}
	
	

}

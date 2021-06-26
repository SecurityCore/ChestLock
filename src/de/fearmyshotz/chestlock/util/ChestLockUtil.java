package de.fearmyshotz.chestlock.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import de.fearmyshotz.chestlock.ChestLock;
import de.fearmyshotz.chestlock.resources.ChestData;

public class ChestLockUtil {
	
	public static void createNewChest(Player p, Block b) {
		int x = b.getLocation().getBlockX();
		int y = b.getLocation().getBlockY();
		int z = b.getLocation().getBlockZ();
		
		String chestName = "X" + x + "Y" + y + "Z" + z;
		
		ChestData.get().set("Chests." + chestName + ".owner", p.getName());
		ChestData.save();
		
		List<String> accessList = new ArrayList<String>();
		accessList.add("null");
		
		ChestData.get().set("Chests." + chestName + ".access", accessList);
		ChestData.save();
		
		p.sendMessage(ChestLock.prefix + "§aAuf diese Kiste hast nur du Zugriff. Mit /addchest kannst du anderen Zugriff geben, Mit /removechest kannst du ihn wieder entfernen.");
	}
	
	@SuppressWarnings("unchecked")
	public static void addAccess(Player p, Player performer) {
		Block targetChest = performer.getTargetBlock((Set<Material>) null, 5);
		int x = targetChest.getLocation().getBlockX();
		int y = targetChest.getLocation().getBlockY();
		int z = targetChest.getLocation().getBlockZ();
		
		String chestName = "X" + x + "Y" + y + "Z" + z;
		
		List<String> accessList = (List<String>) ChestData.get().getList("Chests." + chestName + ".access");
		
		if(accessList.contains(p.getUniqueId().toString())) {
			performer.sendMessage(ChestLock.prefix + "§cDer Spieler " + p.getName() + " hat bereits Zugriff auf die Kiste!");
			return;
		} else {
			accessList.add(p.getUniqueId().toString());
			ChestData.get().set("Chests." + chestName + ".access", accessList);
			ChestData.save();
			
			performer.sendMessage(ChestLock.prefix + "§aDer Spieler " + p.getName() + " hat nun Zugriff auf die Kiste!");
			return;
		}
	}

	@SuppressWarnings("unchecked")
	public static boolean hasAccess(Player p, Block b) {
		int x = b.getLocation().getBlockX();
		int y = b.getLocation().getBlockY();
		int z = b.getLocation().getBlockZ();
		
		String chestName = "X" + x + "Y" + y + "Z" + z;
		
		List<String> accessList = (List<String>) ChestData.get().getList("Chests." + chestName + ".access");
		
		return (accessList.contains(p.getName()) || ChestData.get().getString("Chests." + chestName + ".owner").equals(p.getName()));
	}
	
	@SuppressWarnings("unchecked")
	public static void removeAccess(Player p, Player performer) {
		Block targetChest = performer.getTargetBlock((Set<Material>) null, 5);
		int x = targetChest.getLocation().getBlockX();
		int y = targetChest.getLocation().getBlockY();
		int z = targetChest.getLocation().getBlockZ();
		
		String chestName = "X" + x + "Y" + y + "Z" + z;
		
		List<String> accessList = (List<String>) ChestData.get().getList("Chests." + chestName + ".access");
		
		if(accessList.contains(p.getUniqueId().toString())) {
			accessList.remove(p.getUniqueId().toString());
			ChestData.get().set("Chests." + chestName + ".access", accessList);
			ChestData.save();
			
			performer.sendMessage(ChestLock.prefix + "§aDer Spieler " + p.getName() + " nun keinen Zugriff mehr auf die Kiste!");
			return;
		} else {
			performer.sendMessage(ChestLock.prefix + "§aDer Spieler " + p.getName() + " keinen Zugriff auf die Kiste!");
			return;
		}
	}
	
	public static String getOwner(Block b) {
		int x = b.getLocation().getBlockX();
		int y = b.getLocation().getBlockY();
		int z = b.getLocation().getBlockZ();
		
		String chestName = "X" + x + "Y" + y + "Z" + z;
		
		return ChestData.get().getString("Chests." + chestName + ".owner");
	}
	
	public static boolean isOwner(Player p, Block b) {
		int x = b.getLocation().getBlockX();
		int y = b.getLocation().getBlockY();
		int z = b.getLocation().getBlockZ();
		
		String chestName = "X" + x + "Y" + y + "Z" + z;
		
		return (ChestData.get().getString("Chests." + chestName + ".owner").equals(p.getName()));
	}
}

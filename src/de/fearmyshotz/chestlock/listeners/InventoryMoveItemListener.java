package de.fearmyshotz.chestlock.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;

import de.fearmyshotz.chestlock.ChestLock;
import de.fearmyshotz.chestlock.util.ChestLockUtil;

public class InventoryMoveItemListener implements Listener {
	
	@EventHandler
	public void onItemPickup(final InventoryMoveItemEvent e) {
		ChestLock.log("122");
		if(e.getSource().getType() == InventoryType.CHEST && e.getDestination().getType() == InventoryType.HOPPER) {
			Location hopperLoc = e.getSource().getLocation();
			
			Location possibleChestLocation = new Location(hopperLoc.getWorld(), hopperLoc.getBlockX(), hopperLoc.getBlockY() + 1, hopperLoc.getBlockZ());
			
			ChestLock.log("Test12344");
			
			if(hopperLoc.getWorld().getBlockAt(possibleChestLocation).getType() == Material.CHEST) {
				if(ChestLockUtil.isChestRegistered(hopperLoc.getWorld().getBlockAt(possibleChestLocation))) {
					e.setCancelled(true);
					hopperLoc.getBlock().setType(Material.AIR);
				}
			}
		}
	}

}

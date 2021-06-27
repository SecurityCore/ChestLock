package de.fearmyshotz.chestlock.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.fearmyshotz.chestlock.ChestLock;
import de.fearmyshotz.chestlock.util.ChestLockUtil;

public class BlockPlaceListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockPlace(final BlockPlaceEvent e) {
		if(e.getBlock().getType() == Material.CHEST) {
			ChestLockUtil.createNewChest(e.getPlayer(), e.getBlock());
			Block chestBlock = e.getBlock();
			Chest chestState = (Chest) chestBlock.getState();
			chestState.setCustomName("Truhe von " + e.getPlayer().getName());
			chestState.update();
		}
		if(e.getBlock().getType() == Material.HOPPER) {
			Location hopperLoc = e.getBlock().getLocation();
			
			Location possibleChestLocation = new Location(hopperLoc.getWorld(), hopperLoc.getBlockX(), hopperLoc.getBlockY() + 1, hopperLoc.getBlockZ());
			
			if(possibleChestLocation.getBlock().getType() == Material.CHEST) {
				if(ChestLockUtil.isChestRegistered(possibleChestLocation.getBlock())) {
					if(!ChestLockUtil.getOwner(possibleChestLocation.getBlock()).equals(e.getPlayer().getName())) {
						if(!ChestLockUtil.hasAccess(e.getPlayer(), possibleChestLocation.getBlock())) {
							e.setCancelled(true);
							hopperLoc.getBlock().setType(Material.AIR);
							ChestLock.log("Der Spieler " 
									+ e.getPlayer().getName() 
									+ " hat versucht, bei"
									+ " X" + hopperLoc.getBlockX() 
									+ " Y" + hopperLoc.getBlockY() 
									+ " Z" + hopperLoc.getBlockZ() 
									+ " einen Trichter unter der Truhe von " + ChestLockUtil.getOwner(possibleChestLocation.getBlock()) + " zu platzieren!");
							
							if(e.getPlayer().getGameMode() == GameMode.SURVIVAL) e.getPlayer().damage(2);
							e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
						}
					}
				}
			}
		}
	}
}

package de.fearmyshotz.chestlock.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.fearmyshotz.chestlock.ChestLock;
import de.fearmyshotz.chestlock.util.ChestLockUtil;

public class BlockBreakListener implements Listener {
	
	@EventHandler
	public void onBlockBreak(final BlockBreakEvent e) {
		if(e.getBlock().getType() == Material.CHEST) {
			if(ChestLockUtil.isOwner(e.getPlayer(), e.getBlock())) {
				e.setCancelled(false);
			} else {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChestLock.prefix + "§cNur der Besitzer der Kiste darf sie abbauen!");
			}
		}
	}
}

package de.fearmyshotz.chestlock.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import de.fearmyshotz.chestlock.util.ChestLockUtil;

public class BlockPlaceListener {
	
	@EventHandler
	public void onBlockPlace(final BlockPlaceEvent e) {
		if(e.getBlock().getType() == Material.CHEST) {
			ChestLockUtil.createNewChest(e.getPlayer(), e.getBlock());
		}
	}
}

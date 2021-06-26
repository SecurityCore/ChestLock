package de.fearmyshotz.chestlock.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.fearmyshotz.chestlock.util.ChestLockUtil;

public class BlockPlaceListener implements Listener {
	
	@EventHandler
	public void onBlockPlace(final BlockPlaceEvent e) {
		if(e.getBlock().getType() == Material.CHEST) {
			ChestLockUtil.createNewChest(e.getPlayer(), e.getBlock());
			Block chestBlock = e.getBlock();
			Chest chestState = (Chest) chestBlock.getState();
			chestState.setCustomName("Truhe von " + e.getPlayer().getName());
			chestState.update();
		}
	}
}

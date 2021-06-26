package de.fearmyshotz.chestlock.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.fearmyshotz.chestlock.ChestLock;
import de.fearmyshotz.chestlock.util.ChestLockUtil;

public class PlayerInteractListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.CHEST) {
				if(ChestLockUtil.hasAccess(e.getPlayer(), e.getClickedBlock())) {
					e.setCancelled(false);
				} else {
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChestLock.prefix + "§cDu hast keinen Zugriff auf die Kiste!");
				}
			}
		}
	}
}

package com.theminequest.MineQuest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ParserListener implements Listener {
	public List<BlockListener> listeners;
	
	public ParserListener() {
		listeners = new ArrayList<BlockListener>();
	}
	
	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
		if ((event.getClickedBlock() != null) && (event.getClickedBlock().getType() != Material.AIR)) {
			for (BlockListener listener : listeners) {
				if (listener.player.equals(event.getPlayer().getName())) {
					listener.blockClicked(event.getClickedBlock().getLocation());
				}
			}
		}
	}
}

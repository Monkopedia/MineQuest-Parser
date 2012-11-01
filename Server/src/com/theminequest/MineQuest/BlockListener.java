package com.theminequest.MineQuest;

import org.bukkit.Location;

public abstract class BlockListener {
	public String player;
	
	public BlockListener(String player) {
		this.player = player;
	}
	
	public abstract void blockClicked(Location loc);
}

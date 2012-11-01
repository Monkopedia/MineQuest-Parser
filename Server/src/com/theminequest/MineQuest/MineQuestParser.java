package com.theminequest.MineQuest;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import com.dreamcrushed.ServerComm.NetworkManager;

public class MineQuestParser extends JavaPlugin {

	private NetworkManager networkManager;
	public ParserListener listener;

	@Override
	public void onEnable() {
		File dir = new File("quests");
		if (!dir.exists()) {
			dir.mkdir();
			getServer().getLogger().log(Level.WARNING, "Creating dir quests");
		}
		this.listener = new ParserListener();
		try {
			networkManager = new NetworkManager(26000, this);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		networkManager.start();
		getServer().getPluginManager().registerEvents(listener, this);
		getServer().getLogger().log(Level.WARNING, "MineQuest Parser has started and opened port 26000 for connections");
	}
	
	@Override
	public void onDisable() {
		networkManager.stopp();
		getServer().getLogger().log(Level.WARNING, "Port 26000 closed");
	}
}

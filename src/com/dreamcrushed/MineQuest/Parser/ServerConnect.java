package com.dreamcrushed.MineQuest.Parser;

import java.io.IOException;
import java.net.UnknownHostException;

import com.dreamcrushed.ClientComm.NetworkManager;
import com.dreamcrushed.MineQuest.Parser.Display.DisplayManager;

public class ServerConnect {

	public ServerConnect(DisplayManager displayManager, QuestParser questParser) {
		try {
			displayManager.networkManager = new NetworkManager("localhost", 2200);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

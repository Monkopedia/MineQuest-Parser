package com.dreamcrushed.MineQuest.Parser;

import com.dreamcrushed.MineQuest.Parser.Display.DisplayManager;
import com.dreamcrushed.MineQuest.Parser.Display.ErrorMessage;

public class ServerDisconnect {

	public ServerDisconnect(DisplayManager display,
			QuestParser questParser) {
		if (display.networkManager != null) {
			display.networkManager.disconnect();
			display.networkManager = null;
			new ErrorMessage("Disconnected Successfully!", "Notification", 200,
					display.frame, display.frame.getX() + display.frame.getWidth() / 2,
					display.frame.getY() + display.frame.getHeight() / 2);
		} else {
			new ErrorMessage("Not Connected...", "Error", 200,
					display.frame, display.frame.getX() + display.frame.getWidth() / 2,
					display.frame.getY() + display.frame.getHeight() / 2);
		}
	}

}

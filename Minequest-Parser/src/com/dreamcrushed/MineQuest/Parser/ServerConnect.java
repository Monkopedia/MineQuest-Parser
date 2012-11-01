package com.dreamcrushed.MineQuest.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import com.dreamcrushed.ClientComm.LoginException;
import com.dreamcrushed.ClientComm.NetworkManager;
import com.dreamcrushed.ClientComm.Packets.GetPlayers;
import com.dreamcrushed.MineQuest.Parser.Display.DisplayManager;
import com.dreamcrushed.MineQuest.Parser.Display.YesNoQuestion;

public class ServerConnect {

	public ServerConnect(final DisplayManager display, QuestParser questParser) {
		new ServerHostnameSelect("localhost:26000", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					final String value = e.getActionCommand();
					if (display.networkManager != null) {
						new YesNoQuestion("Disconnect current connection?", new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								try {
									connect(display, value);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}, 300, display.frame, display.frame.getX() + display.frame.getWidth() / 2,
						display.frame.getY() + display.frame.getHeight() / 2);
					} else {
						connect(display, value);
					}
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}, display.frame.getX() + display.frame.getWidth() / 2,
		display.frame.getY() + display.frame.getHeight() / 2);
	}

	protected void connect(DisplayManager display, String value) throws UnknownHostException, IOException {
		String[] split = value.split(":");
		String host = split[0];
		int port = 26000;
		if (split.length > 1) {
			port = Integer.parseInt(split[1]);
		}
		display.networkManager = new NetworkManager(host, port);
		display.networkManager.start();
		try {
			display.networkManager.sendPacket(new GetPlayers());
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

}

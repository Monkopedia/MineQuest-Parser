package com.dreamcrushed.ClientComm.Packets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.dreamcrushed.ClientComm.NetworkManager;
import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;
import com.dreamcrushed.MineQuest.Parser.Display.ErrorMessage;

public class NoPlayerSet extends Packet {
	private static ActionListener listener;

	public NoPlayerSet(int id, String[] params) {
		super(params);
	}
	
	@Override
	public void handle(NetworkManager networkManager) {
		super.handle(networkManager);
		
		if (listener != null) {
			listener.actionPerformed(new ActionEvent(this, 0, ""));
		}
		new ErrorMessage("No Player Set", "Error", 200,
				null, 0, 0);
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.NO_PLAYER_SET.getType() + ""}, ":");
	}

	public static void setPlayerListener(ActionListener actionListener) {
		NoPlayerSet.listener = actionListener;
	}

}

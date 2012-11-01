package com.dreamcrushed.ClientComm.Packets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.dreamcrushed.ClientComm.NetworkManager;
import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class PlayerList extends Packet {
	private static ActionListener listener;
	private String names;

	public PlayerList(int id, String[] params) {
		super(params);
		if (params.length > 0) {
			this.names = params[0];
		}
	}
	
	@Override
	public void handle(NetworkManager networkManager) {
		super.handle(networkManager);
		
		System.out.print("PlayerHandle");
		
		if (listener != null) {
			System.out.println("Callback Sent");
			listener.actionPerformed(new ActionEvent(this, 0, names));
		} else {
			System.out.println("No Callback");
		}
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.PLAYER_LIST.getType() + ""}, ":");
	}

	public static void setPlayerListener(ActionListener actionListener) {
		System.out.println("Callback Set");
		PlayerList.listener = actionListener;
	}

}

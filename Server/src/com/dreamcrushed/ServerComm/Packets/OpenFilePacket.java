package com.dreamcrushed.ServerComm.Packets;

import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.User;

public class OpenFilePacket extends Packet {

	private String filename;

	public OpenFilePacket(int id, String[] params) {
		super(params);
		filename = "quests/" + params[0];
	}
	
	@Override
	public void handle(User user) {
		super.handle(user);
		
		user.openFileWrite(filename);
	}

	@Override
	public String packetString() {
		return null;
	}

}

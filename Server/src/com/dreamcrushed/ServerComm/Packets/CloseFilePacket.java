package com.dreamcrushed.ServerComm.Packets;

import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.User;

public class CloseFilePacket extends Packet {

	public CloseFilePacket(int id, String[] params) {
		super(params);
	}
	
	@Override
	public void handle(User user) {
		super.handle(user);
		
		user.closeFile();
	}

	@Override
	public String packetString() {
		return null;
	}

}

package com.dreamcrushed.ServerComm.Packets;

import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.User;

public class WriteFilePacket extends Packet {

	private String line;

	public WriteFilePacket(int id, String[] params) {
		super(params);
		if (params.length > 0) {
			line = params[0];
			for (int i = 1; i < params.length; i++) {
				line = line + ":" + params[i];
			}
		} else {
			line = "";
		}
	}
	
	@Override
	public void handle(User user) {
		super.handle(user);
		
		user.writeLine(line);
	}

	@Override
	public String packetString() {
		return null;
	}

}

package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.LoginException;
import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.User;
import com.dreamcrushed.ClientComm.Util;

public class IntLocReq extends Packet {

	public IntLocReq(int id, String[] params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(User user) {
		try {
			if (user.player != null) {
				user.sendPacket(new IntLoc());
			} else {
				user.sendPacket(new NoPlayerSet());
			}
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.INT_LOC_REQ.getType() + ""}, ":");
	}

}

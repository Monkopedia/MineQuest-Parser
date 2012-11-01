package com.dreamcrushed.ServerComm.Packets;

import com.dreamcrushed.ServerComm.LoginException;
import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.PacketType;
import com.dreamcrushed.ServerComm.User;
import com.dreamcrushed.ServerComm.Util;

public class LongLocReq extends Packet {

	public LongLocReq(int id, String[] params) {
		super(params);
	}
	
	@Override
	public void handle(User user) {
		try {
			if (user.player != null) {
				user.sendPacket(new LongLoc());
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
		return Util.concat(new String[] {PacketType.LONG_LOC_REQ.getType() + ""}, ":");
	}

}

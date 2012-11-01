package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class IntLoc extends Packet {

	public IntLoc(int id, String[] params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.INT_LOC.getType() + ""}, ":");
	}

}

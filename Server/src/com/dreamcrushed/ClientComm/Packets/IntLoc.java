package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class IntLoc extends Packet {
	int x, y, z;

	public IntLoc() {
		super(new String[0]);
		x = 3;
		y = 2;
		z = 1;
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.INT_LOC.getType() + "", x + "", y + "", z + ""}, ":");
	}

}

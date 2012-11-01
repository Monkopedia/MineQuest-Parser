package com.dreamcrushed.ServerComm.Packets;

import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.PacketType;
import com.dreamcrushed.ServerComm.Util;

public class LongLoc extends Packet {
	float x,y,z,r;

	public LongLoc() {
		super(new String[0]);
		x = 1.5f;
		y = 2.6f;
		z = 3.7f;
		r = 0f;
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.LONG_LOC.getType() + "", x + "", y + "", z + "", r + ""}, ":");
	}

}

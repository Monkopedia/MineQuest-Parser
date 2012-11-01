package com.dreamcrushed.ServerComm.Packets;

import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.PacketType;
import com.dreamcrushed.ServerComm.Util;

public class IntLoc extends Packet {
	int x, y, z;

	public IntLoc(int x, int y, int z) {
		super(new String[0]);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.INT_LOC.getType() + "", x + "", y + "", z + ""}, ":");
	}

}

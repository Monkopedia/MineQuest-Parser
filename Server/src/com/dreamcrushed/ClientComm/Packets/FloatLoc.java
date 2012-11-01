package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class FloatLoc extends Packet {
	float x,y,z;

	public FloatLoc() {
		super(new String[0]);
		x = 1.5f;
		y = 2.6f;
		z = 3.7f;
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.FLOAT_LOC.getType() + "", x + "", y + "", z + ""}, ":");
	}

}

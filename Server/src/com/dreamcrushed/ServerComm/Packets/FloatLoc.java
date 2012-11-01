package com.dreamcrushed.ServerComm.Packets;

import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.PacketType;
import com.dreamcrushed.ServerComm.Util;

public class FloatLoc extends Packet {
	float x,y,z;

	public FloatLoc(double d, double e, double f) {
		super(new String[0]);
		this.x = (float)d;
		this.y = (float)e;
		this.z = (float)f;
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.FLOAT_LOC.getType() + "", x + "", y + "", z + ""}, ":");
	}

}

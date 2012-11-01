package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class FloatLocReq extends Packet {

	public FloatLocReq(int id, String[] params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.FLOAT_LOC_REQ.getType() + ""}, ":");
	}

}

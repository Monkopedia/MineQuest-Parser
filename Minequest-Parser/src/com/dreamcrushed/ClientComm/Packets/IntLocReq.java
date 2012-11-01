package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class IntLocReq extends Packet {

	public IntLocReq() {
		super(new String[0]);
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.INT_LOC_REQ.getType() + ""}, ":");
	}

}

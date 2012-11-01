package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class WriteFilePacket extends Packet {

	private String line;

	public WriteFilePacket(String line) {
		super(new String[0]);
		this.line = line;
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.WRITE_FILE.getType() + "", line}, ":");
	}

}

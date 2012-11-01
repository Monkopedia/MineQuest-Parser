package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class OpenFilePacket extends Packet {
	private String filename;

	public OpenFilePacket(String filename) {
		super(new String[0]);
		this.filename = filename;
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.OPEN_FILE.getType() + "", filename}, ":");
	}

}

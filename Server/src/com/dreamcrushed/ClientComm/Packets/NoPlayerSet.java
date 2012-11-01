package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class NoPlayerSet extends Packet {

	public NoPlayerSet() {
		super(new String[0]);
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.NO_PLAYER_SET.getType() + ""}, ":");
	}

}

package com.dreamcrushed.ServerComm.Packets;

import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.PacketType;
import com.dreamcrushed.ServerComm.Util;

public class NoPlayerSet extends Packet {

	public NoPlayerSet() {
		super(new String[0]);
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.NO_PLAYER_SET.getType() + ""}, ":");
	}

}

package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class GetPlayers extends Packet {

	public GetPlayers() {
		super(new String[0]);
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.GET_PLAYERS.getType() + ""}, ":");
	}

}

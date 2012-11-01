package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.NetworkManager;
import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class GetPlayers extends Packet {

	public GetPlayers(int id, String[] params) {
		super(params);
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.UID.getType() + "", NetworkManager.getUserId() + ""}, ":");
	}

}

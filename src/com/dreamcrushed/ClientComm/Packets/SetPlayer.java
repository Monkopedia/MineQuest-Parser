package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.NetworkManager;
import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class SetPlayer extends Packet {
	private String player;

	public SetPlayer(int id, String[] params) {
		super(params);
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.UID.getType() + "", NetworkManager.getUserId() + "", player}, ":");
	}

}

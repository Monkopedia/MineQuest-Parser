package com.dreamcrushed.ClientComm.Packets;

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
		return Util.concat(new String[] {PacketType.SET_PLAYER.getType() + "", player}, ":");
	}

}

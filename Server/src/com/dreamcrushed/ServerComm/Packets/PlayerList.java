package com.dreamcrushed.ServerComm.Packets;

import java.util.List;

import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.PacketType;
import com.dreamcrushed.ServerComm.Util;

public class PlayerList extends Packet {
	private List<String> names;

	public PlayerList(List<String> names) {
		super(new String[0]);
		this.names = names;
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.PLAYER_LIST.getType() + "", Util.concat(names, ",")}, ":");
	}

}

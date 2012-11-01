package com.dreamcrushed.ClientComm.Packets;

import java.util.ArrayList;
import java.util.List;

import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class PlayerList extends Packet {
	private List<String> names;

	public PlayerList() {
		super(new String[0]);
		names = new ArrayList<String>();
		names.add("jmonk");
		names.add("B3T5Y");
		names.add("fostro");
		names.add("bobclone4");
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.PLAYER_LIST.getType() + "", Util.concat(names, ",")}, ":");
	}

}

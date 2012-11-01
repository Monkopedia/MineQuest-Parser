package com.dreamcrushed.ServerComm.Packets;

import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.PacketType;
import com.dreamcrushed.ServerComm.User;
import com.dreamcrushed.ServerComm.Util;

public class SetPlayer extends Packet {
	private String player;

	public SetPlayer(int id, String[] params) {
		super(params);
		player = params[0];
	}
	
	@Override
	public void handle(User user) {
		user.player = player;
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.SET_PLAYER.getType() + "", player}, ":");
	}

}

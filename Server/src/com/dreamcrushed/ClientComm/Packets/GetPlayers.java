package com.dreamcrushed.ClientComm.Packets;

import com.dreamcrushed.ClientComm.LoginException;
import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.User;
import com.dreamcrushed.ClientComm.Util;

public class GetPlayers extends Packet {

	public GetPlayers(int id, String[] params) {
		super(params);
	}
	
	@Override
	public void handle(User user) {
		try {
			user.sendPacket(new PlayerList());
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.GET_PLAYERS.getType() + ""}, ":");
	}

}

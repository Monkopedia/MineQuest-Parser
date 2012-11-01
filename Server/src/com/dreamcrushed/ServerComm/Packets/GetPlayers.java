package com.dreamcrushed.ServerComm.Packets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.dreamcrushed.ServerComm.LoginException;
import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.PacketType;
import com.dreamcrushed.ServerComm.User;
import com.dreamcrushed.ServerComm.Util;

public class GetPlayers extends Packet {

	public GetPlayers(int id, String[] params) {
		super(params);
	}
	
	@Override
	public void handle(User user) {
		try {
			List<String> names = new ArrayList<String>();
			
			for (Player player : user.plugin.getServer().getOnlinePlayers()) {
				names.add(player.getName());
			}
			user.sendPacket(new PlayerList(names));
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.GET_PLAYERS.getType() + ""}, ":");
	}

}

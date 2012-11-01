package com.dreamcrushed.ServerComm.Packets;

import org.bukkit.entity.Player;

import com.dreamcrushed.ServerComm.LoginException;
import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.PacketType;
import com.dreamcrushed.ServerComm.User;
import com.dreamcrushed.ServerComm.Util;

public class FloatLocReq extends Packet {

	public FloatLocReq(int id, String[] params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(User user) {
		try {
			if (user.player != null) {
				Player player = user.plugin.getServer().getPlayer(user.player);
				if (player != null) {
					user.sendPacket(new FloatLoc(player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ()));
				} else {
					user.sendPacket(new NoPlayerSet());
				}
			} else {
			}
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.FLOAT_LOC_REQ.getType() + ""}, ":");
	}

}

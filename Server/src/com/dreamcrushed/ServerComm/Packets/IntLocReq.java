package com.dreamcrushed.ServerComm.Packets;

import org.bukkit.Location;

import com.dreamcrushed.ServerComm.LoginException;
import com.dreamcrushed.ServerComm.Packet;
import com.dreamcrushed.ServerComm.PacketType;
import com.dreamcrushed.ServerComm.User;
import com.dreamcrushed.ServerComm.Util;
import com.theminequest.MineQuest.BlockListener;

public class IntLocReq extends Packet {

	public IntLocReq(int id, String[] params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(final User user) {
		if (user.player != null) {
			user.plugin.listener.listeners.add(new BlockListener(user.player) {
				@Override
				public void blockClicked(Location loc) {
					try {
						user.sendPacket(new IntLoc(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
					} catch (LoginException e) {
						e.printStackTrace();
					}
				}
			});
		} else {
			try {
				user.sendPacket(new NoPlayerSet());
			} catch (LoginException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.INT_LOC_REQ.getType() + ""}, ":");
	}

}

package com.dreamcrushed.ClientComm.Packets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.dreamcrushed.ClientComm.NetworkManager;
import com.dreamcrushed.ClientComm.Packet;
import com.dreamcrushed.ClientComm.PacketType;
import com.dreamcrushed.ClientComm.Util;

public class FloatLoc extends Packet {

	public static float[] location;
	private static ActionListener listener;

	public FloatLoc(int id, String[] params) {
		super(params);
		location = new float[3];
		for (int i = 0; i < 3; i++) {
			location[i] = Float.parseFloat(params[i]);
		}
	}
	
	@Override
	public void handle(NetworkManager networkManager) {
		super.handle(networkManager);
		
		if (listener != null) {
			listener.actionPerformed(new ActionEvent(this, 0, ""));
		}
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.FLOAT_LOC.getType() + ""}, ":");
	}

	public static void setLocListener(ActionListener actionListener) {
		FloatLoc.listener = actionListener;
	}

}

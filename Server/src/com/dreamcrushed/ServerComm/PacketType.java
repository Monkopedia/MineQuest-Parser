package com.dreamcrushed.ServerComm;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.dreamcrushed.ServerComm.Packets.CloseFilePacket;
import com.dreamcrushed.ServerComm.Packets.FloatLoc;
import com.dreamcrushed.ServerComm.Packets.FloatLocReq;
import com.dreamcrushed.ServerComm.Packets.GetPlayers;
import com.dreamcrushed.ServerComm.Packets.IntLoc;
import com.dreamcrushed.ServerComm.Packets.IntLocReq;
import com.dreamcrushed.ServerComm.Packets.LongLoc;
import com.dreamcrushed.ServerComm.Packets.LongLocReq;
import com.dreamcrushed.ServerComm.Packets.NoPlayerSet;
import com.dreamcrushed.ServerComm.Packets.OpenFilePacket;
import com.dreamcrushed.ServerComm.Packets.PlayerList;
import com.dreamcrushed.ServerComm.Packets.SetPlayer;
import com.dreamcrushed.ServerComm.Packets.WriteFilePacket;


/**
 * This enum will contain the type of every packet in existence.
 * It will also contain information like the class, type id, and
 * name.
 * 
 * @author jmonk
 */
public enum PacketType {
	LOGIN(1, LoginPacket.class),
	LOGIN_FAILED(3, LoginFailedPacket.class),
	GET_PLAYERS(4, GetPlayers.class),
	PLAYER_LIST(5, PlayerList.class),
	SET_PLAYER(6, SetPlayer.class),
	NO_PLAYER_SET(7, NoPlayerSet.class),
	INT_LOC_REQ(8, IntLocReq.class),
	INT_LOC(9, IntLoc.class),
	FLOAT_LOC_REQ(10, FloatLocReq.class),
	FLOAT_LOC(11, FloatLoc.class),
	LONG_LOC_REQ(12, LongLocReq.class),
	LONG_LOC(12, LongLoc.class),
	OPEN_FILE(13, OpenFilePacket.class),
	WRITE_FILE(14, WriteFilePacket.class),
	CLOSE_FILE(15, CloseFilePacket.class),
	;
	
	private int type;
	private Class<? extends Packet> cl;
	private static Map<Integer, PacketType> types = new HashMap<Integer, PacketType>();

	private PacketType(int type, Class<? extends Packet> cl) {
		this.cl = cl;
		this.type = type;
	}
	
	/**
	 * Getter method used to get the integer type of the PacketType.
	 * 
	 * @return Type Id
	 */
	public int getType() {
		return type;
	}

	/**
	 * Get the PacketType associated with the given type id.
	 * 
	 * @param type Type id of Packet
	 * @return PacketType
	 */
	public static PacketType fromInt(int type) {
		return types.get(type);
	}

	/**
	 * This creates an instance of the PacketType. This will only
	 * work if the packet has a constructor that takes (int, String[]).
	 * Otherwise an exception will be thrown. Any packets designed for
	 * network transfer should have the given constructor.
	 *
	 * @param id Packet id (0 for local)
	 * @param params Any packet specific data
	 * @return new Packet
	 */
	public Packet instance(int id, String[] params) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Constructor<? extends Packet> m = cl.getConstructor(int.class, String[].class);
		
		return m.newInstance(id, params);
	}
	
	static {
		for (PacketType type : values()) {
			types.put(type.type, type);
		}
	}
}

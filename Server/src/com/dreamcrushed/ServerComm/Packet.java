package com.dreamcrushed.ServerComm;


import java.lang.reflect.InvocationTargetException;

/**
 * This is the basic unit of communication between the TerraSoul
 * server and client. Classes extend packet and are designed to
 * be self handling through the handle method. They also have
 * unique states where they are transferred to a string and back
 * for network transfer implemented using the packetString and
 * newPacket methods.
 * 
 * @author jmonk
 */
public abstract class Packet {

	public Packet(String[] params) {
	}

	/**
	 * Creates an instance of a packet based on the type contained in the
	 * packet string.
	 * 
	 * @param s Packet String
	 * @return Packet
	 */
	public static Packet newPacket(String s) throws InvalidPacketException {
		String[] split = s.split(":");
		PacketType type = PacketType.fromInt(Integer.parseInt(split[0]));
		if (type == null) {
			throw new InvalidPacketException();
		}
		String[] params = new String[split.length - 1];
		for (int i = 1; i < split.length; i++) {
			params[i - 1] = split[i];
		}
		try {
			return type.instance(0, params);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new InvalidPacketException();
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new InvalidPacketException();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new InvalidPacketException();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new InvalidPacketException();
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new InvalidPacketException();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new InvalidPacketException();
		} catch (InvocationTargetException e) {
			e.getTargetException().printStackTrace();
			throw new InvalidPacketException();
		}
	}

	/**
	 * Handles any client side parsing required by this packet. Could be anything
	 * from sending the user a message to modifying health of a character on screen.
	 * Should be overloaded in any packet which is to do anything.
	 *
	 * @param user
	 */
	public void handle(User user) {
		user.removePacket(this);
	}

	/**
	 * Turn this packet into a string that can be transferred over the network.
	 * 
	 * @return Packet String
	 */
	public abstract String packetString();

}

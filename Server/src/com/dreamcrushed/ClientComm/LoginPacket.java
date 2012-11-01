package com.dreamcrushed.ClientComm;



public class LoginPacket extends Packet{
	private String user;
	private String pass;

	public LoginPacket(String user, String pass) {
		super(new String[0]);
		this.user = user;
		this.pass = pass;
	}

	@Override
	public String packetString() {
		return Util.concat(new String[] {PacketType.LOGIN.getType() + "", user, pass}, ":");
	}

}

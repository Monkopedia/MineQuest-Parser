package com.dreamcrushed.ClientComm;


public class LoginFailedPacket extends Packet {

	private String error;

	public LoginFailedPacket(int id, String[] params) {
		super(params);
		this.error = params[0];
	}
	
	@Override
	public void handle(User user) {
		super.handle(user);
		System.out.println("Error: " + this.error);
		// TODO: Handle Error Here
	}

	@Override
	public String packetString() {
		// Incoming only...
		return Util.concat(new String[] {PacketType.LOGIN.getType() + ""}, ":");
	}

}

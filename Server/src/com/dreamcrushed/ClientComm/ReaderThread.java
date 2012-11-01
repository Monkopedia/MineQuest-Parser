package com.dreamcrushed.ClientComm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReaderThread extends Thread {
	private InputStream inputStream;
	public boolean mRun;
	private BufferedReader bufferedReader;
	private User user;

	public ReaderThread(InputStream inputStream, User user) {
		this.inputStream = inputStream;
		this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
		this.user = user;
		mRun = true;
	}

	public void removePacket(Packet packet) {
		
	}
	
	public void run() {
		System.out.println("Reader Thread Starting Up...");
		while (mRun) {
			String packet;
			try {
				packet = this.bufferedReader.readLine();
				
				Packet p = Packet.newPacket(packet);
				p.handle(user);
			} catch (IOException e) {
				user.stop();
			} catch (InvalidPacketException e) {
				e.printStackTrace();
			}
		}
	}

}

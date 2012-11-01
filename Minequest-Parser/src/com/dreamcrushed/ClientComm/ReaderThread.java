package com.dreamcrushed.ClientComm;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReaderThread extends Thread {
	private InputStream inputStream;
	public boolean mRun;
	private BufferedReader bufferedReader;
	private NetworkManager networkManager;

	public ReaderThread(InputStream inputStream, NetworkManager networkManager) {
		this.inputStream = inputStream;
		this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
		this.networkManager = networkManager;
		mRun = true;
	}

	public void removePacket(Packet packet) {
		
	}
	
	public void run() {
		System.out.println("Reader Thread Starting Up...");
		while (mRun) {
			try {
				String packet = this.bufferedReader.readLine();
				System.out.println("Got Packet: " + packet);
				Packet p = Packet.newPacket(packet);
				p.handle(networkManager);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

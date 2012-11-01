package com.dreamcrushed.ClientComm;

import java.io.IOException;
import java.net.Socket;

public class User {
	public String player;
	private ReaderThread reader;
	private WriterThread writer;
	private Socket socket;
	private NetworkManager manager;
	
	public User(Socket socket, NetworkManager manager) {
		this.socket = socket;
		this.manager = manager;
	}

	/**
	 * This starts up both of the networking threads (reader and writer).
	 * They will remain asleep most of the time. The reader thread will read
	 * packets at the set read rate and the writer thread will remain asleep
	 * except for when packets are in the send queue.
	 * @throws IOException 
	 */
	public void start() throws IOException {
		if (reader != null) {
			reader.mRun = false;
		}
		reader = new ReaderThread(this.socket.getInputStream(), this);
		if (writer != null) {
			writer.mRun = false;
		}
		writer = new WriterThread(this.socket.getOutputStream());
		reader.start();
		writer.start();
	}
	
	/**
	 * This stops both of the network threads. The reader thread will finish
	 * reading any packets already downloaded from the server and parsing those,
	 * while the writer thread will immediately transmit all packets in its queue
	 * followed by shutting down.
	 */
	public void stop() {
		reader.mRun = false;
		writer.mRun = false;
		manager.removeUser(this);
	}

	/**
	 * This adds a packet to the send queue of the network writer thread.
	 * The packet will be sent at the next available time in the thread's
	 * queue.
	 * 
	 * @param packet Packet to be sent
	 * @throws LoginException If user is not logged in.
	 */
	public void sendPacket(Packet packet) throws LoginException {
		writer.sendPacket(packet);
	}

	/**
	 * This adds a packet to the remove list of the network reader thread.
	 * If a packet is not removed before the next read performed by the reader
	 * thread then the packet will be received and handled again.
	 * 
	 * @param packet Packet to remove
	 */
	public void removePacket(Packet packet) {
		reader.removePacket(packet);
	}
}

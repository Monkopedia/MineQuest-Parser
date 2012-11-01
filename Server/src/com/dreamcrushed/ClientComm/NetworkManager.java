package com.dreamcrushed.ClientComm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class NetworkManager extends Thread {
	public static NetworkManager instance;
	private List<User> users;
	private ServerSocket socket;
	public boolean mRun = true;

	public NetworkManager(int port) throws UnknownHostException, IOException {
		instance = this;
		this.socket = new ServerSocket(port);
		users = new ArrayList<User>();
	}
	
	@Override
	public void run() {
		mRun = true;
		while (mRun) {
			try {
				Socket s = socket.accept();
				
				users.add(new User(s, this));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		mRun = false;
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeUser(User user) {
		users.remove(user);
	}
	
	public static final void main(String[] args) {
		System.out.println("Startup");
		NetworkManager nm;
		try {
			nm = new NetworkManager(26000);
			nm.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

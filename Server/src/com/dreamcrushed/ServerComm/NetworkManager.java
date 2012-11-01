package com.dreamcrushed.ServerComm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.theminequest.MineQuest.MineQuestParser;

public class NetworkManager extends Thread {
	public static NetworkManager instance;
	private List<User> users;
	private ServerSocket socket;
	public boolean mRun = true;
	private MineQuestParser plugin;

	public NetworkManager(int port, MineQuestParser mineQuestParser) throws UnknownHostException, IOException {
		instance = this;
		this.socket = new ServerSocket(port);
		users = new ArrayList<User>();
		this.plugin = mineQuestParser;
	}
	
	@Override
	public void run() {
		mRun = true;
		while (mRun) {
			try {
				Socket s = socket.accept();
				
				User user = new User(s, this, plugin);
				user.start();
				users.add(user);
			} catch (Exception e) {
			}
		}
	}
	
	public void stopp() {
		for (User user : users) {
			user.stopp();
		}
		mRun = false;
		try {
			socket.close();
		} catch (IOException e) {
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
		user.stop();
		users.remove(user);
	}
}

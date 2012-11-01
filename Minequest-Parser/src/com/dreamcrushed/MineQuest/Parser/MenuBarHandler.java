package com.dreamcrushed.MineQuest.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.dreamcrushed.ClientComm.LoginException;
import com.dreamcrushed.ClientComm.Packets.CloseFilePacket;
import com.dreamcrushed.ClientComm.Packets.OpenFilePacket;
import com.dreamcrushed.ClientComm.Packets.WriteFilePacket;
import com.dreamcrushed.MineQuest.Parser.Comm.PlayerSelect;
import com.dreamcrushed.MineQuest.Parser.Comm.ServerConnect;
import com.dreamcrushed.MineQuest.Parser.Comm.ServerDisconnect;
import com.dreamcrushed.MineQuest.Parser.Display.DisplayManager;
import com.dreamcrushed.MineQuest.Parser.Display.ErrorMessage;

public class MenuBarHandler {
	private JMenuBar menuBar;
	private QuestParser questParser;
	private DisplayManager display;

	public MenuBarHandler(final DisplayManager displayManager, final QuestParser questParser) {
		//Where the GUI is created:
		JMenu menu;
		JMenuItem menuItem;
		this.questParser = questParser;
		this.display = displayManager;
		menuBar = new JMenuBar();
		

		//Create the menu bar.

		//Build the first menu.
		menu = new JMenu("File");
//		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("New Quest");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "Create a new quest");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DisplayManager.openParser(new QuestParser(null));
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Open Quest");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "Open an existing quest");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser(".");
				int returnVal = fc.showOpenDialog(menuBar);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            DisplayManager.openParser(new QuestParser(file.getAbsolutePath()));
		        }
			}
		});
		menu.add(menuItem);

		if (questParser != null) {
			menuItem = new JMenuItem("Save Quest");
			menuItem.setAccelerator(KeyStroke.getKeyStroke(
			        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			menuItem.getAccessibleContext().setAccessibleDescription(
			        "Save the current quest");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					final JFileChooser fc = new JFileChooser(".");
					int returnVal = fc.showSaveDialog(menuBar);
	
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            questParser.saveQuest(file.getAbsolutePath());
			        }
				}
			});
			menu.add(menuItem);
		}

		//Build second menu in the menu bar.
		menu = new JMenu("Server");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription("Funtions related to server connections");
		menuBar.add(menu);

		menuItem = new JMenuItem("Connect");
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "Connect to a MineQuest Server");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ServerConnect(displayManager, questParser);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Disconnect");
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "Disconnect from current server");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ServerDisconnect(displayManager, questParser);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Select Player");
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "Select current player to track");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new PlayerSelect(displayManager, questParser);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Save and Upload");
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "Save current quest to server");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveAndUpload();
			}
		});
		menu.add(menuItem);
	}
	
	public JMenuBar getMenu() {
		return menuBar;
	}
	
	public void saveAndUpload() {
		if (display.networkManager == null) {
			new ErrorMessage("Not Connected to Server", "Error", 200,
					display.frame, display.frame.getX() + display.frame.getWidth() / 2,
					display.frame.getY() + display.frame.getHeight() / 2);
			return;
		}
		final JFileChooser fc = new JFileChooser(".");
		int returnVal = fc.showSaveDialog(menuBar);
		String fileName;

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            fileName = file.getAbsolutePath();
            questParser.saveQuest(fileName);

            try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
	            display.networkManager.sendPacket(new OpenFilePacket(file.getName()));
	            String line = br.readLine();
	            while (line != null) {
	            	display.networkManager.sendPacket(new WriteFilePacket(line));
	            	line = br.readLine();
	            }
	            display.networkManager.sendPacket(new CloseFilePacket());
	            br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (LoginException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
}

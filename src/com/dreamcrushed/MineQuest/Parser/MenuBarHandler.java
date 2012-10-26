package com.dreamcrushed.MineQuest.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBarHandler {
	public static JMenuBar createMenu(final QuestParser questParser) {
		//Where the GUI is created:
		final JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
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
				DisplayManager.run(new QuestParser(null));
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
		            DisplayManager.run(new QuestParser(file.getAbsolutePath()));
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
		menu.add(menuItem);

		menuItem = new JMenuItem("Disconnect");
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "Disconnect from current server");
		menu.add(menuItem);

		return menuBar;
	}
}

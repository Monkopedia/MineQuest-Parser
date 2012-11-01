package com.dreamcrushed.MineQuest.Parser.Display;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Message {

	private JFrame frame;

	public Message(String message, String window, int width, Component parent, int x, int y) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel(message);
		label.setSize(width, 25);
		label.setLocation(0, 0);
		panel.add(label);
		
		panel.setSize(width, 30);
		panel.setPreferredSize(panel.getSize());
		
		frame = new JFrame(window);
//		final Popup popup = PopupFactory.getSharedInstance().getPopup(parent, panel, x, y);
//		popup.show();
		frame.setContentPane(panel);
		frame.setSize(panel.getSize());
		frame.pack();
		frame.setLocation(x - width/2, y - panel.getHeight()/2);
		frame.setVisible(true);
	}
	
	public void close() {
		frame.dispose();
	}
}

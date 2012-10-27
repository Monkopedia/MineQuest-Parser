package com.dreamcrushed.MineQuest.Parser;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorMessage {

	public ErrorMessage(String message, int width, Component parent, int x, int y) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel(message);
		label.setSize(width, 25);
		label.setLocation(0, 0);
		panel.add(label);
		
		JButton button = new JButton("OK");
		button.setSize(width, 25);
		button.setLocation(0, 25);
		panel.add(button);
		
		panel.setSize(width, 50);
		panel.setPreferredSize(panel.getSize());
		
		final JFrame frame = new JFrame("Error");
//		final Popup popup = PopupFactory.getSharedInstance().getPopup(parent, panel, x, y);
//		popup.show();
		frame.setLocation(x, y);
		frame.setContentPane(panel);
		frame.setSize(panel.getSize());
		frame.pack();
		frame.setVisible(true);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
	}
}

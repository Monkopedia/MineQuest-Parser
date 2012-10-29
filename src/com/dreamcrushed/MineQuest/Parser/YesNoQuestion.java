package com.dreamcrushed.MineQuest.Parser;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class YesNoQuestion {

	public YesNoQuestion(String message, final ActionListener listener, int width, Component parent, int x, int y) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel(message);
		label.setSize(width, 25);
		label.setLocation(0, 0);
		panel.add(label);
		
		final JFrame frame = new JFrame("Verification");
		
		JButton button = new JButton("Yes");
		button.setSize(width/2, 25);
		button.setLocation(0, 25);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listener.actionPerformed(arg0);
				frame.dispose();
			}
		});
		panel.add(button);
		
		button = new JButton("No");
		button.setSize(width/2, 25);
		button.setLocation(width/2, 25);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		panel.add(button);
		
		panel.setSize(width, 50);
		panel.setPreferredSize(panel.getSize());
//		final Popup popup = PopupFactory.getSharedInstance().getPopup(parent, panel, x, y);
//		popup.show();
		frame.setContentPane(panel);
		frame.setSize(panel.getSize());
		frame.pack();
		frame.setLocation(x - width/2, y - panel.getHeight()/2);
		frame.setVisible(true);
	}
}

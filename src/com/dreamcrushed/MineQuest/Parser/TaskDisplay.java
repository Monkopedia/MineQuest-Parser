package com.dreamcrushed.MineQuest.Parser;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TaskDisplay extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	protected int w;
	protected int h;

	public TaskDisplay(final QuestParser parser, final MainPage center, int w, int h) {
		w -= 20;
		h -= 20;
		this.w = w;
		this.h = h;
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setLocation(10, 10);
		scrollPane.setSize(w, h);
		
		panel = new JPanel(new GridLayout(0, 1));
		panel.setLocation(25, 25);
		panel.setSize(w, h);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		scrollPane.setViewportView(panel);
		add(scrollPane);
		
		for (int id : parser.tasks.keySet()) {
			final Task task = parser.tasks.get(id);
			JButton newPanel = new JButton(task.name);
//			newPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			newPanel.setSize(h - 20, 40);
			newPanel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					center.show(task);
				}
			});
			panel.add(newPanel);
		}
		
//		JButton button = new JButton("Add New");
//		button.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//
//			}
//		});
//		button.setLocation(25, 525);
//		button.setSize(250, 25);
//		add(button);
		
        this.setLayout(null);
        this.setSize(300, 575);
        this.setPreferredSize(this.getSize());
	}

}

package com.dreamcrushed.MineQuest.Parser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class FieldDisplay extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QuestParser parser;
	private DisplayManager manager;

	public FieldDisplay(QuestParser parser, DisplayManager manager) {
		this.parser = parser;
		setLayout(null);
//		show(parser.tasks.get(0));
		this.manager = manager;
	}

	public void show() {
		this.removeAll();
		y = 5;
		JLabel l = label("Quest Parameters: ", 0, y, 300, 25);
		l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		y = 0;
		y += 10;
		
		for (final QuestLine line : parser.fields) {
			button(line.getName(), new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
//					new EventDisplay(line, parser, TaskDisplay.this);
					System.out.println("Click");
				}
			}, 300, 25);
			System.out.println(line.getName());
		}
		manager.display(this);
		repaint();
	}

}

package com.dreamcrushed.MineQuest.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TaskDisplay extends BasePage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QuestParser parser;

	public TaskDisplay(QuestParser parser) {
		this.parser = parser;
		setLayout(null);
		show(parser.tasks.get(0));
	}

	public void show(Task task) {
		this.removeAll();
		y = 0;
		label(task.name + ":", 0, y, 300, 25);
		System.out.println(task.name + ":");
		for (final EventLine line : task.events) {
			button(line.getName(), new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new EventDisplay(line, parser);
					System.out.println("Click");
				}
			}, 300, 25);
			System.out.println(line.getName());
		}
		repaint();
	}

}

package com.dreamcrushed.MineQuest.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;


public class TaskDisplay extends BasePage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QuestParser parser;
	public Task currentTask;

	public TaskDisplay(QuestParser parser) {
		this.parser = parser;
		setLayout(null);
		show(parser.tasks.get(0));
	}

	public void show(final Task task) {
		this.currentTask = task;
		this.removeAll();
		y = 0;
		button(task.name + ":", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TaskNameChanger(task, TaskDisplay.this);
			}
		}, 0, 300, 25);
		y = 0;
		final JCheckBox check = checkBox("Repeatable", task.repeating, 300, 0, 300, 35);
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				task.repeating = !task.repeating;
			}
		});
		y += 10;
		
		System.out.println(task.name + ":");
		for (final EventLine line : task.events) {
			button(line.getName(), new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new EventDisplay(line, parser, TaskDisplay.this);
					System.out.println("Click");
				}
			}, 300, 25);
			System.out.println(line.getName());
		}
		repaint();
	}
}

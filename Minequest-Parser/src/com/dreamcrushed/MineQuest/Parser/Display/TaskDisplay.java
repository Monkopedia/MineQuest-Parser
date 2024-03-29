package com.dreamcrushed.MineQuest.Parser.Display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.Task;
import com.dreamcrushed.MineQuest.Parser.Lines.EventLine;


public class TaskDisplay extends BasePage {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected QuestParser parser;
	public Task currentTask;
	public DisplayManager manager;

	public TaskDisplay(QuestParser parser, DisplayManager manager) {
		this.parser = parser;
		setLayout(null);
//		show(parser.tasks.get(0));
		this.manager = manager;
	}

	public void show(final Task task) {
		this.currentTask = task;
		this.removeAll();
		y = 5;
		JLabel l = label("Task: " + task.name, 0, y, 300, 25);
		l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		l.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) 	{	}
			@Override
			public void mousePressed(MouseEvent arg0) 	{	}
			@Override
			public void mouseExited(MouseEvent arg0) 	{	}
			@Override
			public void mouseEntered(MouseEvent arg0) 	{	}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new TaskNameChanger(task, TaskDisplay.this, manager.getX() + manager.getWidth()/2, manager.getY() + manager.getHeight()/2);
			}
		});
		y = 0;
		final JCheckBox check = checkBox("Repeatable", task.repeating, 300, 0, 300, 35);
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				task.repeating = !task.repeating;
			}
		});
		y += 10;
		
		for (final EventLine line : task.events) {
			button(line.getName(), new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						new LineDisplay(line, parser, TaskDisplay.this, manager.getX(), manager.getY());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 300, 25);
		}
		button("Add Event", new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					EventLine line = new EventLine(parser.defs.eventDefs.get(0), parser);
					parser.events.put(line.id, line);
					task.events.add(line);
					show(currentTask);
					new LineDisplay(line, parser, TaskDisplay.this, manager.getX(), manager.getY());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 300, 25);
		manager.display(this);
		repaint();
	}
}

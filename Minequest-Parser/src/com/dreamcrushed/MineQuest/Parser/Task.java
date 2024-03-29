package com.dreamcrushed.MineQuest.Parser;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import com.dreamcrushed.MineQuest.Parser.Lines.EventLine;

public class Task {
	public int id;
	public String name;
	public List<EventLine> events;
	public boolean repeating;
	private JButton callback;

	public Task(int id, boolean repeating) {
		this.id = id;
		this.name = "Task_" + id;
		this.events = new ArrayList<EventLine>();
		this.repeating = repeating;
	}

	public void add(EventLine eventLine) {
		events.add(eventLine);
	}

	public void print(PrintStream ps) {
		if (name != null) {
			ps.println("#" + name);
		}
		if (repeating) {
			ps.print("RepeatingTask:" + id + ":");
		} else {
			ps.print("Task:" + id + ":");
		}
		
		for (int i = 0; i < events.size(); i++) {
			if (i > 0) {
				ps.print(",");
			}
			ps.print(events.get(i).id);
		}
		ps.println();
	}

	public void setHandler(JButton newPanel) {
		this.callback = newPanel;
	}
	
	public void setName(String name) {
		this.name = name;
		if (callback != null) {
			String n = this.name;
			if (n.length() > 25) {
				n = n.substring(0, 22) + "...";
			}
			callback.setText(n);
		}
	}

}

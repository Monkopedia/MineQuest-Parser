package com.dreamcrushed.MineQuest.Parser;

import java.util.ArrayList;
import java.util.List;

public class Task {
	public int id;
	public String name;
	protected List<EventLine> events;
	protected boolean repeating;

	public Task(int id, boolean repeating) {
		this.id = id;
		this.name = "Task_" + id;
		this.events = new ArrayList<EventLine>();
		this.repeating = repeating;
	}

	public void add(EventLine eventLine) {
		events.add(eventLine);
	}

}

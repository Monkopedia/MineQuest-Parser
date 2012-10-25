package com.dreamcrushed.MineQuest.Parser;


public class EventLine extends QuestLine {
	protected EventDefinition eDefinition;
	protected Task nextEvents;
	protected int id;

	public EventLine(EventDefinition definition, String[] fields, int id)
			throws Exception {
		super(definition, fields);
		this.eDefinition = definition;
		this.nextEvents = null;
		this.id = id;
	}

	public Task getNextEvents() {
		return nextEvents;
	}

	public void setNextEvents(Task task) {
		this.nextEvents = task;
	}

	public String getName() {
		return id + ": " + eDefinition.name;
	}

}

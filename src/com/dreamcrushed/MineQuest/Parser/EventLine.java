package com.dreamcrushed.MineQuest.Parser;

import java.io.PrintStream;


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

	public EventLine(EventLine orig) throws Exception {
		super(orig);
		this.id = orig.id;
		this.eDefinition = orig.eDefinition;
	}

	public Task getNextEvents() {
		return nextEvents;
	}

	public void setNextEvents(Task task) {
		this.nextEvents = task;
	}

	@Override
	public String getName() {
		if (name != null) {
			return name;
		} else {
			return id + ": " + eDefinition.name;
		}
	}
	
	@Override
	public void print(PrintStream ps) {
		fields[1] = id + "";
		super.print(ps);
	}

	public void setDefinition(EventDefinition eventDefinition, QuestParser parser) {
		super.setDefinition(eventDefinition);
		this.eDefinition = eventDefinition;
		
		int i = 0;
		for (FieldDefinition fDef : eventDefinition.fields) {
			if (fDef.field == Type.TASK) {
				try {
					setNextEvents(parser.tasks.get(Integer.parseInt(fields[i])));
				} catch (Exception e) {
					fields[i] = "0";
					setNextEvents(parser.tasks.get(Integer.parseInt(fields[i])));
				}
			} else {
				i += fDef.field.length;
			}
		}
	}

	@Override
	public void copy(QuestLine orig) {
		super.copy(orig);
		if (orig instanceof EventLine) {
			this.id = ((EventLine)orig).id;
			this.eDefinition = ((EventLine)orig).eDefinition;
		}
	}

}

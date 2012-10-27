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
		super(orig.definition, new String[orig.fields.length]);
		this.name = orig.name;
		this.id = orig.id;
		for (int i = 0; i < fields.length; i++) {
			fields[i] = orig.fields[i];
		}
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
		String[] newFields = new String[eventDefinition.getLength()];
		for (int i = 0; (i < newFields.length); i++) {
			if (i < fields.length) {
				newFields[i] = fields[i];
			} else {
				newFields[i] = "";
			}
		}
		this.fields = newFields;
		
		this.eDefinition = eventDefinition;
		this.definition = eventDefinition;
		
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

	public void copy(EventLine orig) {
		this.fields = new String[orig.fields.length];
		this.definition = orig.definition;
		this.name = orig.name;
		this.id = orig.id;
		for (int i = 0; i < fields.length; i++) {
			fields[i] = orig.fields[i];
		}
		this.eDefinition = orig.eDefinition;
	}

}

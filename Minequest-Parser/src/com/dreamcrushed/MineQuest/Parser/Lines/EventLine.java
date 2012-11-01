package com.dreamcrushed.MineQuest.Parser.Lines;

import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.Task;
import com.dreamcrushed.MineQuest.Parser.Type;
import com.dreamcrushed.MineQuest.Parser.Definitions.EventDefinition;
import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;


public class EventLine extends EnumeratedLine {
	protected EventDefinition eDefinition;
	protected Task nextEvents;

	public EventLine(EventDefinition definition, String[] fields, int id)
			throws Exception {
		super(definition, fields, id);
		this.eDefinition = definition;
		this.nextEvents = null;
	}

	public EventLine(EventLine orig) throws Exception {
		super(orig);
		this.id = orig.id;
		this.eDefinition = orig.eDefinition;
	}
	
	public EventLine(EventDefinition definition, QuestParser parser) {
		super(definition, parser);
		this.eDefinition = definition;
		this.nextEvents = null;
	}

	public Task getNextEvents() {
		return nextEvents;
	}

	public void setNextEvents(Task task) {
		this.nextEvents = task;
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

	@Override
	public int allocateId(QuestParser parser) {
		return parser.allocateEventId();
	}

}

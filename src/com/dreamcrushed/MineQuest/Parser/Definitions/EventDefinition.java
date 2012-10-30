package com.dreamcrushed.MineQuest.Parser.Definitions;

public class EventDefinition extends QuestDefinition {

	public EventDefinition(String name, FieldDefinition[] fields) {
		super(name, fields);
	}

	public boolean hasTask() {
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isLinkable()) {
				return true;
			}
		}
		return false;
	}
	
	public int getTask(String[] line) {
		int l = 0;
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isLinkable()) {
				return Integer.parseInt(line[l]);
			}
			l += fields[i].field.length;
		}
		return -2;
	}

}

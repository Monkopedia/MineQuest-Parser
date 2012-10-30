package com.dreamcrushed.MineQuest.Parser.Definitions;

public class QuestDefinition {
	public String name;
	public FieldDefinition[] fields;
	
	public QuestDefinition(String name, FieldDefinition[] fields) {
		this.name = name;
		this.fields = fields;
	}

	public int getLength() {
		int l = 0;
		for (int i = 0; i < fields.length; i++) {
			l += fields[i].field.length;
		}
		return l;
	}
}

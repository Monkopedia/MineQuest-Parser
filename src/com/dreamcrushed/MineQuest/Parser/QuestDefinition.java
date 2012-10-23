package com.dreamcrushed.MineQuest.Parser;

public class QuestDefinition {
	public String name;
	public FieldDefinition[] fields;
	
	public QuestDefinition(String name, FieldDefinition[] fields) {
		this.name = name;
		this.fields = fields;
	}
}

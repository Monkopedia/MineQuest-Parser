package com.dreamcrushed.MineQuest.Parser;

public class QuestLine {
	protected String[] fields;
	protected QuestDefinition definition;
	
	public QuestLine(QuestDefinition definition, String[] fields) throws Exception {
		if (definition.getLength() != fields.length) {
			throw new Exception("Invalid number of fields");
		}
		this.definition = definition;
		this.fields = fields;
	}
}

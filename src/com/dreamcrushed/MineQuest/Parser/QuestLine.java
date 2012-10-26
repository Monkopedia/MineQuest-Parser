package com.dreamcrushed.MineQuest.Parser;

import java.io.PrintStream;

public class QuestLine {
	protected String[] fields;
	protected QuestDefinition definition;
	public String name = null;
	
	public QuestLine(QuestDefinition definition, String[] fields) throws Exception {
		if (definition.getLength() != fields.length) {
			throw new Exception("Invalid number of fields");
		}
		this.definition = definition;
		this.fields = fields;
	}

	public void print(PrintStream ps) {
		if (name != null) {
			ps.println("#" + name);
		}
		for (int i = 0; i < fields.length; i++) {
			if (i > 0) {
				ps.print(":");
			}
			ps.print(fields[i]);
		}
		ps.println();
	}
}

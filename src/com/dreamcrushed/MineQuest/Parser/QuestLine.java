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
	
	public QuestLine(QuestLine orig) throws Exception {
		this.name = orig.name;
		this.definition = orig.definition;
		for (int i = 0; i < fields.length; i++) {
			fields[i] = orig.fields[i];
		}
	}
	
	public String getName() {
		return definition.name;
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

	public void copy(QuestLine orig) {
		this.fields = new String[orig.fields.length];
		this.definition = orig.definition;
		this.name = orig.name;
		for (int i = 0; i < fields.length; i++) {
			fields[i] = orig.fields[i];
		}
	}
}

package com.dreamcrushed.MineQuest.Parser.Lines;

import java.io.PrintStream;

import com.dreamcrushed.MineQuest.Parser.Definitions.QuestDefinition;

public class QuestLine {
	public String[] fields;
	public QuestDefinition definition;
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
		this.fields = new String[orig.fields.length];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = orig.fields[i];
		}
	}
	
	public QuestLine(QuestDefinition definition) {
		this.definition = definition;
		this.fields = new String[definition.getLength()];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = "";
		}
		fields[0] = definition.name.replaceAll(" ", "").replaceAll(" \\(T\\)", "");
	}

	public void setDefinition(QuestDefinition questDefinition) {
		String[] newFields = new String[questDefinition.getLength()];
		for (int i = 0; (i < newFields.length); i++) {
			if (i < fields.length) {
				newFields[i] = fields[i];
			} else {
				newFields[i] = "";
			}
		}
		this.fields = newFields;
		
		this.definition = questDefinition;
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

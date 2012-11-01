package com.dreamcrushed.MineQuest.Parser.Lines;

import java.io.PrintStream;

import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.Definitions.QuestDefinition;

public abstract class EnumeratedLine extends QuestLine {
	public int id;

	public EnumeratedLine(QuestDefinition definition, QuestParser parser) {
		super(definition);
		id = allocateId(parser);
		fields[2] = definition.name.replaceAll(" ", "").replaceAll(" \\(T\\)", "");
		fields[0] = getClass().getSimpleName().replaceAll("Line", "");
	}

	public EnumeratedLine(QuestLine orig) throws Exception {
		super(orig);
	}
	
	public EnumeratedLine(QuestDefinition definition, String[] fields, int id) throws Exception {
		super(definition, fields);
		this.id = id;
	}

	@Override
	public void print(PrintStream ps) {
		fields[1] = id + "";
		super.print(ps);
	}

	@Override
	public String getName() {
		if (name != null) {
			return name;
		} else {
			return id + ": " + definition.name;
		}
	}
	
	@Override
	public void setDefinition(QuestDefinition questDefinition) {
		super.setDefinition(questDefinition);
		fields[2] = definition.name.replaceAll(" ", "").replaceAll(" \\(T\\)", "");
	}
	
	public abstract int allocateId(QuestParser parser);

}

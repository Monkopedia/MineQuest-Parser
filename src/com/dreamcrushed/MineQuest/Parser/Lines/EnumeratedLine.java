package com.dreamcrushed.MineQuest.Parser.Lines;

import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.Definitions.QuestDefinition;

public abstract class EnumeratedLine extends QuestLine {
	public int id;

	public EnumeratedLine(QuestDefinition definition, QuestParser parser) {
		super(definition);
		id = allocateId(parser);
	}

	public EnumeratedLine(QuestDefinition definition, String[] fields)
			throws Exception {
		super(definition, fields);
	}

	public EnumeratedLine(QuestLine orig) throws Exception {
		super(orig);
	}
	
	public abstract int allocateId(QuestParser parser);

}

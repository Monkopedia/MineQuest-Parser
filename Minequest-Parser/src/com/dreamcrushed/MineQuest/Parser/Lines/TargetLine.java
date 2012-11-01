package com.dreamcrushed.MineQuest.Parser.Lines;

import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.Definitions.QuestDefinition;

public class TargetLine extends EnumeratedLine {

	public TargetLine(QuestDefinition definition, QuestParser parser) {
		super(definition, parser);
	}

	public TargetLine(QuestDefinition definition, String[] fields)
			throws Exception {
		super(definition, fields);
	}

	public TargetLine(QuestLine orig) throws Exception {
		super(orig);
	}

	@Override
	public int allocateId(QuestParser parser) {
		return parser.allocateTargetId();
	}

}

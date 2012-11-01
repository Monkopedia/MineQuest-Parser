package com.dreamcrushed.MineQuest.Parser.Lines;

import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.Definitions.QuestDefinition;

public class RequirementLine extends EnumeratedLine {

	public RequirementLine(QuestDefinition definition, QuestParser parser) {
		super(definition, parser);
	}

	public RequirementLine(QuestDefinition definition, String[] fields, int id) throws Exception {
		super(definition, fields, id);
	}

	public RequirementLine(QuestLine orig) throws Exception {
		super(orig);
	}

	@Override
	public int allocateId(QuestParser parser) {
		return parser.allocateRequirementId();
	}

}

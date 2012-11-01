package com.dreamcrushed.MineQuest.Parser.Lines;

import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.Definitions.QuestDefinition;

public class EditLine extends EnumeratedLine {

	public EditLine(QuestDefinition definition, QuestParser parser) {
		super(definition, parser);
	}

	public EditLine(QuestLine orig) throws Exception {
		super(orig);
	}

	public EditLine(QuestDefinition definition, String[] fields, int id) throws Exception {
		super(definition, fields, id);
	}

	@Override
	public int allocateId(QuestParser parser) {
		return parser.allocateEditId();
	}

}

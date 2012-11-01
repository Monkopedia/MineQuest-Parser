package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class QuestFieldHandler extends SpinnerHandler {

	public QuestFieldHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		super(line, display, fDef, display.parser.defs.questDefs);
	}

}

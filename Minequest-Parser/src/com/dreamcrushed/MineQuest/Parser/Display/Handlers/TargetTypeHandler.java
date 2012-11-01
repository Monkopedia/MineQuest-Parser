package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class TargetTypeHandler extends SpinnerHandler {

	public TargetTypeHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		super(line, display, fDef, display.parser.defs.targetDefs);
	}

}

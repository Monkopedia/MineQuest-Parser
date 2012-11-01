package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class CompareHandler extends StringSpinnerHandler {

	public CompareHandler(QuestLine line, LineDisplay display,
			FieldDefinition fDef) {
		super(line, display, fDef, new String[] {
				"<",
				">",
				"=",
				"<=",
				">="
		}, line.fields[display.index]);
	}

}

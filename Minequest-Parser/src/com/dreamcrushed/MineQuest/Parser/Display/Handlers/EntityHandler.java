package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import com.dreamcrushed.MineQuest.Parser.EntityType;
import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class EntityHandler extends StringSpinnerHandler {

	public EntityHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		this(line, display, fDef, line.fields[display.index]);
	}
	
	public EntityHandler(QuestLine line, LineDisplay display, FieldDefinition fDef, String start) {
		super(line, display, fDef, EntityType.names, start);
	}

}

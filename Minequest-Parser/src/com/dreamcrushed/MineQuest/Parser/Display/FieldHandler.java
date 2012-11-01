package com.dreamcrushed.MineQuest.Parser.Display;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public abstract class FieldHandler {
	
	protected QuestLine line;
	protected LineDisplay display;
	protected int index;
	protected FieldDefinition fDef;

	public FieldHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		this.line = line;
		this.display = display;
		this.index = display.index;
		this.fDef = fDef;
	}
	
	public abstract void createDisplay();
	
	public abstract void startSave();
	
	public abstract boolean save();
	
	public abstract void completeSave();

	public void tempSave() {
		
	}
	
}

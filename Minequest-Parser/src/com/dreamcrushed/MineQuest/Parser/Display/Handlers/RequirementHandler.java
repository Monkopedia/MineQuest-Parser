package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class RequirementHandler extends StringSpinnerHandler {
	int id;
	protected List<Integer> rids;

	public RequirementHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		this(line, display, fDef, line.fields[display.index]);
	}

	public RequirementHandler(QuestLine line, LineDisplay display, FieldDefinition fDef, String rid) {
		super(line, display, fDef, new ArrayList<String>(), "");
		strings = new String[display.parser.requirements.size()];

		rids = new ArrayList<Integer>();
		int i = 0;
		Set<Integer> keys = display.parser.requirements.keySet();
		for (int tid : keys) {
			strings[i] = tid + " " + display.parser.requirements.get(tid).name;
			rids.add(tid);
			if (rid.equals(tid + ""))
				start = strings[i];
			i++;
		}
	}

	@Override
	protected void select(int selectedIndex) {
		line.fields[index] = rids.get(selectedIndex) + "";
	}

	@Override
	public void startSave() {

	}

	@Override
	public boolean save() {
		return true;
	}

	@Override
	public void completeSave() {
		
	}

}
